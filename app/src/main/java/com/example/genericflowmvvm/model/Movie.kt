package com.example.genericflowmvvm.model

data class Movie(
    val id: Int,
    val original_title: String,
    val backdrop_path: String,
    val poster_path: String,
    val overview: String,
    val adult: Boolean,
    val release_date: String,
    val original_language: String,
    val popularity: Double,
    val vote_average: Double,
    val vote_count: Int
)


data class MovieResponse(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)
