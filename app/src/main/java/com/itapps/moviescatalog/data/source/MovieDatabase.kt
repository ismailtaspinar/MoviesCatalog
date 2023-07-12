package com.itapps.moviescatalog.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.itapps.moviescatalog.data.model.Movie

@Database(
    entities = [Movie::class],
    version = 4
)
abstract class MovieDatabase : RoomDatabase() {
    abstract val movieDao : MovieDao

    companion object{
        const val DATABASE_NAME = "movie_db"
    }
}