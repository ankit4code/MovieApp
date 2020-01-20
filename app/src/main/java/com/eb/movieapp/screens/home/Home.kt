package com.eb.movieapp.screens.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.eb.movieapp.common.ApiService
import com.eb.movieapp.databinding.FragmentHomeBinding
import org.koin.android.ext.android.inject

class Home : Fragment() {

    private val apiService: ApiService by inject()

    private val viewModel: HomeViewModel by viewModels {
        HomeViewModelFactory(apiService)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = FragmentHomeBinding.inflate(inflater,container,false)

        viewModel.call()


        return binding.root
    }
}
