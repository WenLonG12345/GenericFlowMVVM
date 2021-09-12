package com.example.genericflowmvvm.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.genericflowmvvm.databinding.MovieItemBinding
import com.example.genericflowmvvm.model.Movie

class MovieAdapter: ListAdapter<Movie, MovieAdapter.MovieVH>(DiffUtils) {

    inner class MovieVH(private val binding: MovieItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie?) {
            with(binding) {
                movie?.let{ movie ->
                    tvMoviesTitle.text = movie.original_title
                    ivMoviesPoster.load(IMAGE_DOMAIN + movie.backdrop_path)
                }

            }
        }
    }


    companion object{
        const val IMAGE_DOMAIN = "https://image.tmdb.org/t/p/w500/"

        private val DiffUtils = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieVH {
        val binding = MovieItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieVH(binding)
    }

    override fun onBindViewHolder(holder: MovieVH, position: Int) {
        holder.bind(getItem(position))
    }
}