package com.example.moviereview.remotedatasource.datasource

import com.example.moviereview.remotedatasource.data.Movie
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    @GET("svc/movies/v2/reviews/all.json")
    suspend fun getListMovies(
        @Query("api-key") api_key: String,
        @Query("num_results") num_results: Int
    ): Movie
}