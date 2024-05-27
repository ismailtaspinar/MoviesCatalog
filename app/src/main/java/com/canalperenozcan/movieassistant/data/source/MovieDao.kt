package com.canalperenozcan.movieassistant.data.source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.canalperenozcan.movieassistant.data.model.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie WHERE isWatchList = 1")
    fun getWatchListMovies(): List<Movie>

    @Query("SELECT * FROM movie WHERE isFavorite = 1")
    fun getFavoriteMovies(): List<Movie>

    @Query("SELECT EXISTS(SELECT 1 FROM movie WHERE id = :id LIMIT 1)")
    fun isMovieExists(id: Int): Boolean

    @Query("SELECT * FROM movie WHERE id = :id")
    fun getMovieById(id: Int): Movie?

    @Update
    suspend fun updateMovie(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: Movie)

    @Delete
    suspend fun deleteMovie(movie: Movie)
}