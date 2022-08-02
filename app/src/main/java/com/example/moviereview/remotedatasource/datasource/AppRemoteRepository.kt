package com.example.moviereview.remotedatasource.datasource

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.moviereview.remotedatasource.data.Movie
import com.example.moviereview.remotedatasource.data.Result

interface AppRemoteRepository {
    suspend fun getListMovie(): LiveData<PagingData<Result>>
}