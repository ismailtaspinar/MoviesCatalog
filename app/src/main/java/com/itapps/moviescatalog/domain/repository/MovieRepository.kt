package com.itapps.moviescatalog.domain.repository

import com.itapps.moviescatalog.common.Resource
import com.itapps.moviescatalog.data.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun fetchMovies(query : String) : Flow<Resource<List<Movie>>>

    fun fetchDetails(id: String) : Flow<Resource<Movie>>
}