package com.itapps.moviescatalog.domain.source

import com.itapps.moviescatalog.data.model.Movie
import com.itapps.moviescatalog.data.model.MovieResponse

interface RemoteDataSource {
    suspend fun fetchMovies(apiKey: String, query: String, page: Int, perPage: Int): MovieResponse

    suspend fun fetchDetails(id : String) : Movie
}