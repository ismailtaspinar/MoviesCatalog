package com.itapps.moviescatalog.common

import com.itapps.moviescatalog.BuildConfig


object Constants {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val SEARCH = "search/movie"
    const val SLIDER = "movie/now_playing"
    const val UPCOMING = "movie/upcoming"
    const val DETAILS = "movie/{movie_id}"
    const val TOP_RATED = "movie/top_rated"
    const val API_KEY = BuildConfig.API_KEY
    const val BASE_DETAILS = "https://image.tmdb.org/t/p/w500"
    const val BASE_POSTER = "https://image.tmdb.org/t/p/w154"
    const val UPCOMING_POSTER = "https://image.tmdb.org/t/p/w342"
}