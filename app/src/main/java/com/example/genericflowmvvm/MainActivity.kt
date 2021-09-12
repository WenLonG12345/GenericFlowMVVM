package com.example.genericflowmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.genericflowmvvm.databinding.ActivityMainBinding
import com.example.genericflowmvvm.model.ApiStatus.*
import com.example.genericflowmvvm.model.showToast
import com.example.genericflowmvvm.ui.MovieAdapter
import com.example.genericflowmvvm.ui.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MovieViewModel>()
    private val movieAdapter by lazy { MovieAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvMovies.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(context)
        }

        viewModel.getUpcomingMovies().observe(this, { result ->
            binding.progressBar.isVisible = result?.status == LOADING
            when(result?.status) {
                SUCCESS -> {
                    binding.tvEmptySearch.isVisible = result.data == null || result.data.results.isEmpty()
                    movieAdapter.submitList(result.data?.results)
                }
                ERROR -> result.message?.showToast(this)
                LOADING -> Unit
            }
        })
    }
}