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

class HomeViewModel internal constructor(val apiService: ApiService) : BaseViewModel(){

    private val _movieDAO: MutableLiveData<MovieDAO> = MutableLiveData()
    val movieLiveData : LiveData<MovieDAO> get() = _movieDAO

    fun getMovies(){
        launch {

            val movieDAO = withContext(Dispatchers.IO) {
                apiService.getMovies(API_KEY,1)
            }
            _movieDAO.postValue(movieDAO)
        }
    }

}