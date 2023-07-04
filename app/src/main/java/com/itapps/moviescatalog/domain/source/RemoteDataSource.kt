package com.itapps.moviescatalog.domain.source

import com.itapps.moviescatalog.data.model.Movie

interface RemoteDataSource {
    suspend fun fetchMovies(query : String) : List<Movie>

    suspend fun fetchDetails(id : String) : Movie
}