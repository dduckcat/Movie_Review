package com.example.moviereview.di

import com.example.moviereview.presentation.domain.RecyclerInteractor
import com.example.moviereview.presentation.domain.RecyclerInteractorImpl
import com.example.moviereview.remotedatasource.datasource.AppRemoteRepository
import com.example.moviereview.remotedatasource.datasource.AppRemoteRepositoryImpl
import com.example.moviereview.remotedatasource.datasource.MovieAPI
import com.example.moviereview.rest.RetrofitFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideRecyclerInteractor(appRemoteRepository: AppRemoteRepository): RecyclerInteractor {
        return RecyclerInteractorImpl(appRemoteRepository)
    }

    @Singleton
    @Provides
    fun provideMovieApi(retrofitFactory: RetrofitFactory): MovieAPI {
        return retrofitFactory.getApiInterface(MovieAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideAppRemoteRepository(movieAPI: MovieAPI): AppRemoteRepository {
        return AppRemoteRepositoryImpl(movieAPI)
    }
}