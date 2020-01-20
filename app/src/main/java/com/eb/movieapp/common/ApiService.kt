package com.eb.movieapp.common



import com.eb.movieapp.common.models.MovieDAO
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {
    @GET("movie/popular")
    suspend fun getMovies(
        @Header("Authorization") token: String): MovieDAO
}