package com.itapps.moviescatalog.di

import com.itapps.moviescatalog.data.repository.MovieRepoImpl
import com.itapps.moviescatalog.domain.repository.MovieRepository
import com.itapps.moviescatalog.domain.source.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideRepo(remoteDataSource: RemoteDataSource) : MovieRepository = MovieRepoImpl(remoteDataSource)
}