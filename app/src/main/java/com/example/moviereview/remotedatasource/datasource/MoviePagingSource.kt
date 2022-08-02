package com.example.moviereview.remotedatasource.datasource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviereview.app.KEY_FOR_API
import com.example.moviereview.remotedatasource.data.Result

class MoviePagingSource (
    private val service: MovieAPI
) :
    PagingSource<Int, Result>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val position = params.key ?: INITIAL_LOAD_SIZE

        Log.e("qwerty", "position $position paramsKey ${params.key} paramsLoadSize ${params.loadSize}")

        val offset = if (params.key != null) position * params.loadSize else INITIAL_LOAD_SIZE
        return try {
            val response = service.getListMovies(offset = offset, limit = params.loadSize, api_key = KEY_FOR_API).body()!!.results
            val nextKey = if (response.isEmpty()) null else position + (params.loadSize / NETWORK_PAGE_SIZE)
            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = nextKey
            )
        } catch (e: Exception){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return null
    }



    companion object{
        const val NETWORK_PAGE_SIZE = 20
        private const val INITIAL_LOAD_SIZE = 0
    }
}