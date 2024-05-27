package com.canalperenozcan.movieassistant.domain.source

import com.canalperenozcan.movieassistant.data.model.Movie

interface LocalDataSource {
    suspend fun getWatchListMovies(): List<Movie>

    suspend fun getFavoriteMovies(): List<Movie>

    fun isMovieExists(id: Int): Boolean

    suspend fun getMovieById(id: Int): Movie?

    suspend fun updateMovie(movie: Movie)

    suspend fun insertMovie(movie: Movie)

    suspend fun deleteMovie(movie: Movie)
}