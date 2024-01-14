package com.itapps.moviescatalog.data.source

import com.itapps.moviescatalog.common.Constants.DETAILS
import com.itapps.moviescatalog.common.Constants.DISCOVER
import com.itapps.moviescatalog.common.Constants.GENRES
import com.itapps.moviescatalog.common.Constants.RECOMMENDATIONS
import com.itapps.moviescatalog.common.Constants.SEARCH
import com.itapps.moviescatalog.common.Constants.SLIDER
import com.itapps.moviescatalog.common.Constants.TOP_RATED
import com.itapps.moviescatalog.common.Constants.UPCOMING
import com.itapps.moviescatalog.data.model.GenreResponse
import com.itapps.moviescatalog.data.model.Movie
import com.itapps.moviescatalog.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET(SEARCH)
    suspend fun fetchMovies(
        @Query("api_key") api_key : String,
        @Query("query") query : String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ) : MovieResponse

    @GET(DETAILS)
    suspend fun fetchDetails(
        @Path("movie_id") id : String,
        @Query("api_key") api_key : String
    ) : Movie

    @GET(SLIDER)
    suspend fun fetchPlayingMovies(
        @Query("api_key") api_key : String
    ) : MovieResponse

    @GET(UPCOMING)
    suspend fun fetchUpcomingMovies(
        @Query("api_key") api_key : String
    ) : MovieResponse

    @GET(TOP_RATED)
    suspend fun fetchTopMovies(
        @Query("api_key") api_key : String
    ) : MovieResponse

    @GET(GENRES)
    suspend fun getGenres(
        @Query("api_key") api_key : String
    ) : GenreResponse

    @GET(RECOMMENDATIONS)
    suspend fun getRecommendations(
        @Path("movie_id") id : String,
        @Query("api_key") api_key : String
    ) : MovieResponse

    @GET(DISCOVER)
    suspend fun discoverMovies(
        @Query("with_genres") genres : String,
        @Query("api_key") api_key : String
    ) : MovieResponse
}