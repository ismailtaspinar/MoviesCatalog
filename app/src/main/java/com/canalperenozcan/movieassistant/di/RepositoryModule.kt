package com.canalperenozcan.movieassistant.di

import com.canalperenozcan.movieassistant.data.repository.MovieRepoImpl
import com.canalperenozcan.movieassistant.domain.repository.MovieRepository
import com.canalperenozcan.movieassistant.domain.source.LocalDataSource
import com.canalperenozcan.movieassistant.domain.source.RemoteDataSource
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
    fun provideRepo(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
    ) : MovieRepository = MovieRepoImpl(remoteDataSource,localDataSource)
}