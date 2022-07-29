package com.example.moviereview.remotedatasource.datasource

import com.example.moviereview.remotedatasource.data.Movie

interface AppRemoteRepository {
    suspend fun getListMovie(): Movie
}