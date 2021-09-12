package com.example.genericflowmvvm.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.genericflowmvvm.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository
): ViewModel() {

    fun getUpcomingMovies() = movieRepository.getUpcomingMovies().asLiveData()
}