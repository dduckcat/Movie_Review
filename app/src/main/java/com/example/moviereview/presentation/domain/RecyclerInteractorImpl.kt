package com.example.moviereview.presentation.domain

import com.example.moviereview.remotedatasource.data.Movie
import com.example.moviereview.remotedatasource.datasource.AppRemoteRepository
import javax.inject.Inject

class RecyclerInteractorImpl @Inject constructor(
    private val appRemoteRepository: AppRemoteRepository
): RecyclerInteractor {
    override suspend fun getMovie(): Movie {
        return appRemoteRepository.getListMovie()
    }
}