package com.example.genericflowmvvm.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String = "6fb74351a5f00c6df891bb4afd683889",
        @Query("page") page: Int = 1
    ) : Response<MovieResponse>
}