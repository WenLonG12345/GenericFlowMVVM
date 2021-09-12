package com.example.genericflowmvvm.di

import com.example.genericflowmvvm.model.ApiService
import com.example.genericflowmvvm.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideMovieRepository(
        apiService: ApiService
    ) = MovieRepository(apiService)
}