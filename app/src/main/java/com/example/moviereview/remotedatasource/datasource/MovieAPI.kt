package com.example.moviereview.remotedatasource.datasource

import com.example.moviereview.remotedatasource.data.Movie
import com.example.moviereview.remotedatasource.data.Result
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    @GET("svc/movies/v2/reviews/all.json")
    suspend fun getListMovies(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("api-key") api_key: String
    ): Response<Movie>
}