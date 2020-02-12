package com.eb.movieapp.common
import com.eb.movieapp.common.models.MovieDAO
import com.eb.movieapp.common.models.MovieDetailDao
import retrofit2.http.GET
import retrofit2.http.Path

import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getMovies(
        @Query("api_key") apiKey:String,
        @Query("page") page:Int
    ): MovieDAO

    @GET("movie/{id}")
    suspend fun getMovieDetail(
        @Path("id") id:String,
        @Query("api_key") apiKey:String
        ): MovieDetailDao
}