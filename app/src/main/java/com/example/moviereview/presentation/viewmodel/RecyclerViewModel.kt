package com.example.moviereview.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviereview.presentation.domain.RecyclerInteractor
import com.example.moviereview.remotedatasource.data.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class RecyclerViewModel @Inject constructor(
    private val recyclerInteractor: RecyclerInteractor
): ViewModel() {

    private val movieLiveData = MutableLiveData<Movie>()

    fun getMovies(): MutableLiveData<Movie>{
        viewModelScope.launch {
            try {
                movieLiveData.value = recyclerInteractor.getMovie()
            }catch (e:Exception){
                Log.e("qwert","${e.message}")
            }

        }
        return movieLiveData
    }

}