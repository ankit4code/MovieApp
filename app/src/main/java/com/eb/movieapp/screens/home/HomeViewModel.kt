package com.eb.movieapp.screens.home


import com.eb.movieapp.common.ApiService
import com.eb.movieapp.common.base.BaseViewModel

class HomeViewModel internal constructor(val apiService: ApiService) : BaseViewModel(){

    fun call(){
        println(">>>>>>>>>>$apiService")
    }

}