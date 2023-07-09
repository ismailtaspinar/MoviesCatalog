package com.itapps.moviescatalog.domain.source

import com.itapps.moviescatalog.data.model.Movie
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun getWatchListMovies(): List<Movie>

    suspend fun getFavoriteMovies(): List<Movie>

    fun isMovieExists(id: Int): Boolean

    suspend fun getMovieById(id: Int): Movie?

    suspend fun updateMovie(movie: Movie)

    suspend fun insertMovie(movie: Movie)

    suspend fun deleteMovie(movie: Movie)
}