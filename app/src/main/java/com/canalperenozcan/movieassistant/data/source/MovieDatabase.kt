package com.canalperenozcan.movieassistant.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.canalperenozcan.movieassistant.data.model.Movie

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