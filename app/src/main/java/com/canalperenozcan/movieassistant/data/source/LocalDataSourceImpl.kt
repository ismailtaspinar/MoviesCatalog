package com.canalperenozcan.movieassistant.data.source

import com.canalperenozcan.movieassistant.data.model.Movie
import com.canalperenozcan.movieassistant.domain.source.LocalDataSource

class LocalDataSourceImpl(private val movieDao : MovieDao) : LocalDataSource {
    override suspend fun getWatchListMovies(): List<Movie> {
        return movieDao.getWatchListMovies()
    }

    override suspend fun getFavoriteMovies(): List<Movie> {
        return movieDao.getFavoriteMovies()
    }

    override  fun isMovieExists(id: Int): Boolean {
        return movieDao.isMovieExists(id)
    }

    override suspend fun getMovieById(id: Int): Movie? {
        return movieDao.getMovieById(id)
    }

    override suspend fun updateMovie(movie: Movie) {
        return movieDao.updateMovie(movie)
    }

    override suspend fun insertMovie(movie: Movie) {
        return movieDao.insertMovie(movie)
    }

    override suspend fun deleteMovie(movie: Movie) {
        return movieDao.deleteMovie(movie)
    }
}