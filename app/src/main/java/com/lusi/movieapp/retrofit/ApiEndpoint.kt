package com.lusi.movieapp.retrofit

import com.lusi.movieapp.model.DetailResponse
import com.lusi.movieapp.model.MovieResponse
import com.lusi.movieapp.model.TrailerResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiEndpoint {

    @GET("movie/popular")
    fun getMoviePopular (
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ): Call<MovieResponse>

    @GET("movie/now_playing")
    fun getMovieNowPlaying (
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ): Call<MovieResponse>

    @GET("movie/top_rated")
    fun getMovieTopRated (
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ): Call<MovieResponse>

    @GET("movie/upcoming")
    fun getMovieUpcoming (
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ): Call<MovieResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetail (
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String
    ): Call<DetailResponse>

    @GET("movie/{movie_id}/videos")
    fun getMovieTrailer (
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String
    ): Call<TrailerResponse>

}