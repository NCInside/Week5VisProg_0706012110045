package com.example.week5visprog.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.week5visprog.model.Result
import com.example.week5visprog.remote.MovieApi
import com.example.week5visprog.repository.MovieRepository
import com.example.week5visprog.state.MovieDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: MovieRepository
): ViewModel() {

    var state by mutableStateOf(MovieDetailState())

    init  {
        viewModelScope.launch {
            val id = savedStateHandle.get<Int>("id") ?: return@launch
            state = state.copy(isLoading = true)
            repository
                .getMovieDetailsResults(MovieApi.API_KEY, id)
                .let {
                    state = if(it.isSuccessful) {
                        state.copy(
                            movie = it.body(),
                            isLoading = false,
                            error = false
                        )
                    } else {
                        state.copy(
                            movie = null,
                            isLoading = false,
                            error = true
                        )
                    }
                }
        }
    }

}