package com.example.moviereview.presentation.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.moviereview.remotedatasource.data.Movie
import com.example.moviereview.remotedatasource.data.Result
import com.example.moviereview.remotedatasource.datasource.AppRemoteRepository
import javax.inject.Inject

class RecyclerInteractorImpl @Inject constructor(
    private val appRemoteRepository: AppRemoteRepository
): RecyclerInteractor {
    override suspend fun getMovie(): LiveData<PagingData<Result>> {
        return appRemoteRepository.getListMovie()
    }
}