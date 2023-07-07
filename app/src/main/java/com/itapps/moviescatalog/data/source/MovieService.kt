package com.itapps.moviescatalog.data.source

import com.itapps.moviescatalog.common.Constants.DETAILS
import com.itapps.moviescatalog.common.Constants.SEARCH
import com.itapps.moviescatalog.data.model.Movie
import com.itapps.moviescatalog.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET(SEARCH)
    suspend fun fetchMovies(
        @Query("api_key") api_key : String
        ,@Query("query") query : String
    ) : MovieResponse

    @GET(DETAILS)
    suspend fun fetchDetails(
        @Path("movie_id") id : String,
        @Query("api_key") api_key : String
    ) : Movie
}