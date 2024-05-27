package com.canalperenozcan.movieassistant.domain.repository

import androidx.paging.PagingData
import com.canalperenozcan.movieassistant.common.Resource
import com.canalperenozcan.movieassistant.data.model.Movie
import com.canalperenozcan.movieassistant.data.model.MovieResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun fetchMovies(query: String): Flow<PagingData<Movie>>

    fun fetchDetails(id: String) : Flow<Resource<Movie>>

    fun fetchPlayingMovies() : Flow<Resource<MovieResponse>>

    fun fetchUpcomingMovies() : Flow<Resource<MovieResponse>>

    fun fetchTopMovies() : Flow<Resource<MovieResponse>>

    suspend fun getWatchListMovies(): List<Movie>

    suspend fun getFavoriteMovies(): List<Movie>

    suspend fun isMovieExists(id: Int): Resource<Boolean>

    suspend fun getMovieById(id: Int): Movie?

    suspend fun updateMovie(movie: Movie)

    suspend fun insertMovie(movie: Movie)

    suspend fun deleteMovie(movie: Movie)
}