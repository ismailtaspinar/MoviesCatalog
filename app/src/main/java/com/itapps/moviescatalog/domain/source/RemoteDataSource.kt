package com.itapps.moviescatalog.domain.source

import com.itapps.moviescatalog.data.model.Movie
import com.itapps.moviescatalog.data.model.MovieResponse

interface RemoteDataSource {
    suspend fun fetchMovies(query : String) : MovieResponse

    suspend fun fetchDetails(id : String) : Movie
}