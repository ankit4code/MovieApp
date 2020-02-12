package com.eb.movieapp.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eb.movieapp.common.ApiService

class MovieDetailViewModelFactory(private val apiService: ApiService): ViewModelProvider.NewInstanceFactory(){
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = MovieDetailViewModel(apiService) as T
}