package com.canalperenozcan.movieassistant.domain.source

import com.canalperenozcan.movieassistant.data.model.Movie
import com.canalperenozcan.movieassistant.data.model.MovieResponse

interface RemoteDataSource {
    suspend fun fetchMovies(apiKey: String, query: String, page: Int, perPage: Int): MovieResponse

    suspend fun fetchDetails(id : String) : Movie

    suspend fun fetchPlayingMovies(apiKey: String) : MovieResponse

    suspend fun fetchUpcomingMovies(apiKey: String) : MovieResponse

    suspend fun fetchTopMovies(apiKey: String) : MovieResponse
}