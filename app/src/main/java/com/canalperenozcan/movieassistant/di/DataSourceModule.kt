package com.canalperenozcan.movieassistant.di

import com.canalperenozcan.movieassistant.data.source.LocalDataSourceImpl
import com.canalperenozcan.movieassistant.data.source.MovieDao
import com.canalperenozcan.movieassistant.data.source.MovieService
import com.canalperenozcan.movieassistant.data.source.RemoteDataSourceImpl
import com.canalperenozcan.movieassistant.domain.source.LocalDataSource
import com.canalperenozcan.movieassistant.domain.source.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun provideRemoteDataSource(service: MovieService) : RemoteDataSource = RemoteDataSourceImpl(service)

    @Provides
    @Singleton
    fun provideLocalDataSource(dao: MovieDao) : LocalDataSource = LocalDataSourceImpl(dao)
}