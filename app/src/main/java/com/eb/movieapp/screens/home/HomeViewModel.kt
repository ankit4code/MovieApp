package com.eb.movieapp.screens.home


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eb.movieapp.common.ApiService
import com.eb.movieapp.common.base.BaseViewModel
import com.eb.movieapp.common.models.MovieDAO
import com.eb.movieapp.common.utils.API_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel internal constructor(private val apiService: ApiService) : BaseViewModel(){

    private val _movieDAO: MutableLiveData<MovieDAO> = MutableLiveData()
    val movieLiveData : LiveData<MovieDAO> get() = _movieDAO

    init {
        getMovies(1)
    }

    fun getMovies(page:Int){
        launch {
            changeState(load = true)
            val movieDAO = withContext(Dispatchers.IO) {
                apiService.getMovies(API_KEY,page)
            }
            changeState(load = false)
            _movieDAO.postValue(movieDAO)
        }
    }

}