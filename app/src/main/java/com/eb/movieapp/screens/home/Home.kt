package com.eb.movieapp.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.eb.movieapp.adapter.MovAdapter
import com.eb.movieapp.common.ApiService
import com.eb.movieapp.common.extensions.hide
import com.eb.movieapp.common.extensions.show
import com.eb.movieapp.common.models.Movie
import com.eb.movieapp.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.inject

class Home : Fragment() {

    private val apiService: ApiService by inject()
    private var page=1

    var intArray:IntArray= intArrayOf(0,0)

    private var loading = false
    private var list:MutableList<Movie> = mutableListOf()
    lateinit var layoutManager: StaggeredGridLayoutManager

    private val viewModel: HomeViewModel by viewModels {
        HomeViewModelFactory(apiService)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = FragmentHomeBinding.inflate(inflater,container,false)

        val adapter= MovAdapter(list)

        layoutManager = binding.plantList.layoutManager as StaggeredGridLayoutManager

        binding.plantList.adapter = adapter
        
        binding.plantList.addOnScrollListener(setScrollListener())
        subscribeUi(adapter)

        return binding.root
    }

    private fun subscribeUi(adapter: MovAdapter) {
        viewModel.movieLiveData.observe(viewLifecycleOwner, Observer {
            list.addAll(it.results)
            page=it.page+1
            adapter.notifyDataSetChanged()
        })
        viewModel.loading.observe(viewLifecycleOwner, Observer {
            loading=it
            if(it) pb.show() else pb.hide()
        })
    }

    private fun setScrollListener():RecyclerView.OnScrollListener{
        return object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                intArray= layoutManager.findLastCompletelyVisibleItemPositions(intArray)
                if(intArray.isNotEmpty() && !loading)
                    if (intArray[1] >= list.size - 2)
                        viewModel.getMovies(page++)
            }
        }
    }
}
