package com.eb.movieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.eb.movieapp.common.models.Movie
import com.eb.movieapp.databinding.MovieListBinding
import com.eb.movieapp.screens.home.HomeDirections

class MovAdapter(private val list:MutableList<Movie>) : RecyclerView.Adapter<MovAdapter.MovViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovViewHolder {
        return MovViewHolder(
            MovieListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovViewHolder, position: Int) {
        val plant = list[position]
        holder.bind(plant)
    }

    override fun getItemCount() = list.size

    class MovViewHolder(private val binding:MovieListBinding) : RecyclerView.ViewHolder(binding.root){

        init {
            binding.setClickListener {
                binding.movie?.let { movie ->
                    navigateToPlant(movie, it)
                }
            }
        }

        private fun navigateToPlant(movie: Movie, view: View) {

            val directions = HomeDirections.actionHome2ToMovieDetail(movie.id)

            view.findNavController().navigate(directions)
        }

        fun bind(item: Movie) {
            binding.apply {
                movie = item
                executePendingBindings()
            }
        }
    }


}