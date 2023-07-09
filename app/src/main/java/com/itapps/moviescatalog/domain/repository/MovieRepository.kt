package com.itapps.moviescatalog.domain.repository

import com.itapps.moviescatalog.common.Resource
import com.itapps.moviescatalog.data.model.Movie
import com.itapps.moviescatalog.data.model.MovieResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun fetchMovies(query : String) : Flow<Resource<MovieResponse>>

    fun fetchDetails(id: String) : Flow<Resource<Movie>>

    suspend fun getWatchListMovies(): List<Movie>

    suspend fun getFavoriteMovies(): List<Movie>

    fun isMovieExists(id: Int): Boolean

    suspend fun getMovieById(id: Int): Movie?

    suspend fun updateMovie(movie: Movie)

    suspend fun insertMovie(movie: Movie)

    suspend fun deleteMovie(movie: Movie)
}