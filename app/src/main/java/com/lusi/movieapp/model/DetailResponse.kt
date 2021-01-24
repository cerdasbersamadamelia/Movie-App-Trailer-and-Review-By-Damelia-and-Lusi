package com.lusi.movieapp.model

data class DetailResponse (
    val id: Int?,
    val title: String?,
    val backdrop_path: String?,
    val poster_path: String?,
    val overview: String?,
    val genres: List<GenreModel>?,
    val vote_average: Double,
    val release_date: String?,
    val original_language: String?,
    val vote_count: Int?
)