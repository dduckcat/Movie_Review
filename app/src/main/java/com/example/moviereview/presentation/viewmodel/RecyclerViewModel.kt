package com.example.moviereview.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.moviereview.presentation.domain.RecyclerInteractor
import com.example.moviereview.remotedatasource.data.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecyclerViewModel @Inject constructor(
    private val repository: RecyclerInteractor
) : ViewModel() {

    private val movieLiveData = MutableLiveData<PagingData<Result>>()

    suspend fun getMovie(): LiveData<PagingData<Result>> {


        val response = repository.getMovie().cachedIn(viewModelScope)
        movieLiveData.value = response.value

        return response
    }


}