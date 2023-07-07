package com.itapps.moviescatalog.data.source

import com.itapps.moviescatalog.common.Constants.API_KEY
import com.itapps.moviescatalog.data.model.Movie
import com.itapps.moviescatalog.data.model.MovieResponse
import com.itapps.moviescatalog.domain.source.RemoteDataSource

class RemoteDataSourceImpl(private val movieService: MovieService) : RemoteDataSource {
    override suspend fun fetchMovies(query: String): MovieResponse {
        return movieService.fetchMovies(API_KEY,query)
    }

    override suspend fun fetchDetails(id: String): Movie {
        return movieService.fetchDetails(id, API_KEY)
    }

}