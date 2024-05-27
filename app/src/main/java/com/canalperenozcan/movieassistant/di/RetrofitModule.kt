package com.canalperenozcan.movieassistant.di

import com.canalperenozcan.movieassistant.common.Constants.BASE_URL
import com.canalperenozcan.movieassistant.data.source.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    private val client = OkHttpClient.Builder()
        .build()

    @Provides
    @Singleton
    fun provideService() : MovieService = Retrofit.Builder().client(client).baseUrl(
        BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).build().create(MovieService::class.java)
}