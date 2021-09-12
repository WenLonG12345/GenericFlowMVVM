package com.example.genericflowmvvm.repository

import com.example.genericflowmvvm.model.ApiService
import com.example.genericflowmvvm.model.toResultFlow
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val apiService: ApiService
) {

    fun getUpcomingMovies() = toResultFlow {
        apiService.getUpcomingMovies()
    }
}