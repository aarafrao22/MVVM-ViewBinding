package com.aarafrao.mvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.aarafrao.mvvm.adapter.MovieAdapter
import com.aarafrao.mvvm.databinding.ActivityMainBinding
import com.aarafrao.mvvm.viewmodel.MovieViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MovieViewModel
    private lateinit var movieAdapter: MovieAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prepareRecyclerView()
        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]
        viewModel.getPopularMovies()
        viewModel.observeMovieLiveData().observe(this, Observer { movieList ->
            movieAdapter.setMovieList(movieList)
        })
    }

    private fun prepareRecyclerView() {
        movieAdapter = MovieAdapter()
        binding.rvMovies.apply {
            layoutManager = GridLayoutManager(applicationContext, 2)
            adapter = movieAdapter
        }
    }
}