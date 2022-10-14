package com.example.week5visprog.remote

import com.example.week5visprog.model.MovieDetails
import com.example.week5visprog.model.NowPlaying
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ):retrofit2.Response<NowPlaying>

    @GET("movie/{id}")
    suspend fun getMovieDetails(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): retrofit2.Response<MovieDetails>

    companion object {
        const val API_KEY = "955da28e98d1ab556349ce3fb3a9cfc5"
        const val BASE_URL = "https://api.themoviedb.org/3/"
    }
}