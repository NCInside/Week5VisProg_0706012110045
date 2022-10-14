package com.example.week5visprog.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.week5visprog.model.Result
import com.example.week5visprog.remote.MovieApi
import com.example.week5visprog.repository.MovieRepository
import com.example.week5visprog.state.MovieNowPlayingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieNowPlayingViewModel @Inject constructor(
    private val repository: MovieRepository
): ViewModel() {

    var state by mutableStateOf(MovieNowPlayingState())

    init {
        getMovieNowPlaying()
    }

    private fun getMovieNowPlaying(
        apiKey: String = MovieApi.API_KEY,
        language: String = "en-US",
        page: Int = 1
    ) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            repository
                .getNowPlayingResults(apiKey, language, page)
                .let {
                    if(it.isSuccessful) {
                        state = state.copy(
                            movies = it.body()?.results as List<Result>,
                            isLoading = false,
                            error = false
                        )
                    }
                    else {
                        state = state.copy(
                            movies = emptyList(),
                            isLoading = false,
                            error = true
                        )
                    }
                }
        }
    }

}