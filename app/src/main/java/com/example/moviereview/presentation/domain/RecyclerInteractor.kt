package com.example.moviereview.presentation.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.moviereview.remotedatasource.data.Movie
import com.example.moviereview.remotedatasource.data.Result

interface RecyclerInteractor {
    suspend fun getMovie(): LiveData<PagingData<Result>>
}