package com.example.genericflowmvvm

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.genericflowmvvm.databinding.ActivityMainBinding
import com.example.genericflowmvvm.model.ApiResult
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
            binding.progressBar.isVisible = result is ApiResult.Loading
            when(result) {
                is ApiResult.Error -> result.message.showToast(this)
                ApiResult.Loading -> Unit
                is ApiResult.Success -> {
                    binding.tvEmptySearch.isVisible = result.data == null || result.data.results.isEmpty()
                    movieAdapter.submitList(result.data?.results)
                }
            }
        })
    }
}