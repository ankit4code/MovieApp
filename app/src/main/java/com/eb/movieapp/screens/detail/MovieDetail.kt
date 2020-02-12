package com.eb.movieapp.screens.detail


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs

import com.eb.movieapp.R
import com.eb.movieapp.common.ApiService
import com.eb.movieapp.databinding.FragmentMovieDetailBinding
import com.eb.movieapp.screens.home.HomeViewModel
import com.eb.movieapp.screens.home.HomeViewModelFactory
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import org.koin.android.ext.android.inject

class MovieDetail : Fragment(R.layout.fragment_movie_detail) {

    private val args: MovieDetailArgs by navArgs()
    lateinit var binding: FragmentMovieDetailBinding

    private val apiService: ApiService by inject()

    private val movieViewModel: MovieDetailViewModel by viewModels {
        MovieDetailViewModelFactory(apiService)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMovieDetailBinding.bind(view)

        movieViewModel.getMovieDetail(args.id.toString()).observe(viewLifecycleOwner, Observer {
            binding.movieDao=it
        })

        var isToolbarShown = false

        // scroll change listener begins at Y = 0 when image is fully collapsed
        binding.plantDetailScrollview.setOnScrollChangeListener(
            NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, _ ->

                // User scrolled past image to height of toolbar and the title text is
                // underneath the toolbar, so the toolbar should be shown.
                val shouldShowToolbar = scrollY > toolbar.height

                // The new state of the toolbar differs from the previous state; update
                // appbar and toolbar attributes.
                if (isToolbarShown != shouldShowToolbar) {
                    isToolbarShown = shouldShowToolbar

                    // Use shadow animator to add elevation if toolbar is shown
                    appbar.isActivated = shouldShowToolbar

                    // Show the plant name if toolbar is shown
                    binding.toolbarLayout.isTitleEnabled = shouldShowToolbar
                }
            }
        )

        toolbar.setNavigationOnClickListener { view ->
            view.findNavController().navigateUp()
        }

        setHasOptionsMenu(true)

    }
}
