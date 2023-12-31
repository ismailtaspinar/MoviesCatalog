package com.itapps.moviescatalog.di

import com.itapps.moviescatalog.data.source.LocalDataSourceImpl
import com.itapps.moviescatalog.data.source.MovieDao
import com.itapps.moviescatalog.data.source.MovieService
import com.itapps.moviescatalog.data.source.RemoteDataSourceImpl
import com.itapps.moviescatalog.domain.source.LocalDataSource
import com.itapps.moviescatalog.domain.source.RemoteDataSource
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