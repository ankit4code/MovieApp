package com.eb.movieapp.screens.detail


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eb.movieapp.common.ApiService
import com.eb.movieapp.common.base.BaseViewModel
import com.eb.movieapp.common.models.MovieDetailDao
import com.eb.movieapp.common.utils.API_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailViewModel internal constructor(private val apiService: ApiService) : BaseViewModel(){

    private val _movieDAO: MutableLiveData<MovieDetailDao> = MutableLiveData()
    private val movieLiveData : LiveData<MovieDetailDao> get() = _movieDAO

    var movie:MovieDetailDao?=null

    fun getMovieDetail(id:String) : LiveData<MovieDetailDao>{
        launch {
            changeState(load = true)
            movie = withContext(Dispatchers.IO) {
                apiService.getMovieDetail(id,API_KEY)
            }
            changeState(load = false)
            _movieDAO.postValue(movie)
        }
        return movieLiveData
    }

}