package com.example.moviereview.presentation.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import com.example.moviereview.databinding.RecyclerActivityBinding
import com.example.moviereview.presentation.adapter.MovieAdapter
import com.example.moviereview.presentation.viewmodel.RecyclerViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
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

        binding.recyclerView.adapter = adapter

        viewModel.viewModelScope.launch {
            viewModel.getMovie().observe(this@ActivityRecycler){
                adapter.submitData(lifecycle, it)
            }
        }


        //progressBar
        adapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading ||
                loadState.append is LoadState.Loading)
                binding.progressbar.isVisible = true
            else {
                binding.progressbar.isVisible = false
                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error ->  loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                errorState?.let {
                    Toast.makeText(this, it.error.toString(), Toast.LENGTH_LONG).show()
                }

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}




