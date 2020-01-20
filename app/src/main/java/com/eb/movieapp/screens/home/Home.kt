package com.eb.movieapp.screens.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.eb.movieapp.adapter.MovieAdapter
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

        viewModel.getMovies()

        val adapter = MovieAdapter()
        binding.plantList.adapter = adapter

        subscribeUi(adapter)

        return binding.root
    }

    private fun subscribeUi(adapter: MovieAdapter) {
        viewModel.movieLiveData.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it.results)
        })
    }
}
