package com.itapps.moviescatalog.domain.repository

import com.itapps.moviescatalog.common.Resource
import com.itapps.moviescatalog.data.model.Movie
import com.itapps.moviescatalog.data.model.MovieResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun fetchMovies(query : String) : Flow<Resource<MovieResponse>>

    fun fetchDetails(id: String) : Flow<Resource<Movie>>
}