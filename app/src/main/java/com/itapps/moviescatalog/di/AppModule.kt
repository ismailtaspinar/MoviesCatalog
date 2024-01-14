package com.itapps.moviescatalog.di

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.itapps.moviescatalog.data.source.MovieDao
import com.itapps.moviescatalog.data.source.MovieDatabase
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    val migration_4_5 = object : Migration(4, 5) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE movie ADD COLUMN genre_ids TEXT")
        }
    }

    @Provides
    @Singleton
    fun provideMovieDatabase(app: Application): MovieDatabase {
        return Room.databaseBuilder(
            app,
            MovieDatabase::class.java,
            MovieDatabase.DATABASE_NAME
        ).addMigrations(migration_4_5)
            .build()
    }

    @Provides
    @Singleton
    fun provideDao(db: MovieDatabase) : MovieDao = db.movieDao


}