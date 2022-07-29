package com.example.moviereview.presentation.domain

import com.example.moviereview.remotedatasource.data.Movie

interface RecyclerInteractor {
    suspend fun getMovie(): Movie
}