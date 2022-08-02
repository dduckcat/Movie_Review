package com.example.moviereview.remotedatasource.datasource

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.moviereview.app.KEY_FOR_API
import com.example.moviereview.remotedatasource.data.Movie
import com.example.moviereview.remotedatasource.data.Result
import com.example.moviereview.remotedatasource.datasource.MoviePagingSource.Companion.NETWORK_PAGE_SIZE
import javax.inject.Inject

class AppRemoteRepositoryImpl @Inject constructor(
    private val movieAPI: MovieAPI
): AppRemoteRepository {
    override suspend fun getListMovie(): LiveData<PagingData<Result>> {

        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                initialLoadSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                MoviePagingSource(movieAPI)
            }
        ).liveData
    }
}