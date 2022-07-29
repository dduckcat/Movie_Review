package com.example.moviereview.presentation.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.moviereview.databinding.RecyclerActivityBinding
import com.example.moviereview.presentation.adapter.MovieAdapter
import com.example.moviereview.presentation.viewmodel.RecyclerViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ActivityRecycler : AppCompatActivity() {
    private var adapter = MovieAdapter()
    private val viewModel: RecyclerViewModel by viewModels()



    private var _binding: RecyclerActivityBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = RecyclerActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel.getMovies().observe(this) {
            adapter.submitList(it.results)
            adapter.notifyDataSetChanged()
            binding.recyclerView.adapter = adapter
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}