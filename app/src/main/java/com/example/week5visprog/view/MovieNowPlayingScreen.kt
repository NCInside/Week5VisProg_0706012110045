package com.example.week5visprog.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.week5visprog.view.destinations.MovieDetailScreenDestination
import com.example.week5visprog.viewmodel.MovieNowPlayingViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination(start = true)
fun MovieNowPlayingScreen(
    navigator: DestinationsNavigator,
    viewModel: MovieNowPlayingViewModel = hiltViewModel()
) {
    val state = viewModel.state
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(state.movies.size) { i ->
            val movie = state.movies[i]
            MovieItem(
                movie = movie,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navigator.navigate(
                            MovieDetailScreenDestination(movie.id)
                        )
                    }
                    .padding(16.dp)
            )
            if(i < state.movies.size) {
                Divider(modifier = Modifier.padding(
                    horizontal = 16.dp
                ))
            }
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if(state.isLoading) {
            CircularProgressIndicator()
        }
        else if(state.error) {
            Text(
                text = "Error: Get Data Failed",
                color = MaterialTheme.colors.error
            )
        }
    }
}