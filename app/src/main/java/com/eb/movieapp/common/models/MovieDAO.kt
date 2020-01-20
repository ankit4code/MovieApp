package com.eb.movieapp.common.models

data class MovieDAO(
    val page:Int,
    val total_results:Int,
    val total_pages:Int,
    val results:MutableList<Movie>
)

data class Movie(
    val id:Long,
    val popularity:Double,
    val title:String,
    val release_date:String,
    var poster_path:String,
    val overview:String
)