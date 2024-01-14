package com.itapps.moviescatalog.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.itapps.moviescatalog.data.model.Converters
import com.itapps.moviescatalog.data.model.Movie

@Database(
    entities = [Movie::class],
    version = 5
)
@TypeConverters(Converters::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract val movieDao : MovieDao

    companion object{
        const val DATABASE_NAME = "movie_db"
    }
}