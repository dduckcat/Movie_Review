package com.example.moviereview.remotedatasource.datasource

import com.example.moviereview.app.KEY_FOR_API
import com.example.moviereview.remotedatasource.data.Movie
import javax.inject.Inject

class AppRemoteRepositoryImpl @Inject constructor(
    private val movieAPI: MovieAPI
): AppRemoteRepository {
    override suspend fun getListMovie(): Movie {
        return movieAPI.getListMovies(KEY_FOR_API, 21)
    }
}