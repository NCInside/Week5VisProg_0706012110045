package com.example.week5visprog.state

import com.example.week5visprog.model.Result

data class MovieNowPlayingState(
    val movies: List<Result> = emptyList(),
    val isLoading: Boolean = false,
    val error: Boolean = false
)
