package com.canalperenozcan.movieassistant.di

import android.app.Application
import androidx.room.Room
import com.canalperenozcan.movieassistant.data.source.MovieDao
import com.canalperenozcan.movieassistant.data.source.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMovieDatabase(app: Application): MovieDatabase {
        return Room.databaseBuilder(
            app,
            MovieDatabase::class.java,
            MovieDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigrationOnDowngrade()
            .build()
    }

    @Provides
    @Singleton
    fun provideDao(db: MovieDatabase) : MovieDao = db.movieDao



}