package com.example.week5visprog.repository

import com.example.week5visprog.remote.MovieApi
import javax.inject.Inject

class MovieRepository @Inject constructor(private val api: MovieApi){

    suspend fun getNowPlayingResults(apiKey: String, language:String, page:Int) = api.getNowPlaying(apiKey, language, page)

    suspend fun getMovieDetailsResults(apiKey: String, id: Int) = api.getMovieDetails(id, apiKey)
}