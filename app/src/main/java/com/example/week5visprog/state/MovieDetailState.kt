package com.example.week5visprog.state

import com.example.week5visprog.model.MovieDetails

data class MovieDetailState(
    val movie: MovieDetails? = null,
    val isLoading: Boolean = false,
    val error: Boolean = false
)
