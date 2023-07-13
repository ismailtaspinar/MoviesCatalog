package com.itapps.moviescatalog.data.source

import com.itapps.moviescatalog.common.Constants.API_KEY
import com.itapps.moviescatalog.data.model.Movie
import com.itapps.moviescatalog.data.model.MovieResponse
import com.itapps.moviescatalog.domain.source.RemoteDataSource

class RemoteDataSourceImpl(private val movieService: MovieService) : RemoteDataSource {
    override suspend fun fetchMovies(apiKey: String, query: String, page: Int, perPage: Int): MovieResponse {
        return movieService.fetchMovies(apiKey, query, page, perPage)
    }

    override suspend fun fetchDetails(id: String): Movie {
        return movieService.fetchDetails(id, API_KEY)
    }

    override suspend fun fetchPlayingMovies(apiKey: String): MovieResponse {
        return movieService.fetchPlayingMovies(apiKey)
    }

    override suspend fun fetchUpcomingMovies(apiKey: String): MovieResponse {
        return movieService.fetchUpcomingMovies(apiKey)
    }

    override suspend fun fetchTopMovies(apiKey: String): MovieResponse {
        return movieService.fetchTopMovies(apiKey)
    }

}