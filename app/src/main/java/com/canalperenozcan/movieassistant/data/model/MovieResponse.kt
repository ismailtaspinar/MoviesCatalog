package com.canalperenozcan.movieassistant.data.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("page") val page : String?,
    @SerializedName("results")val data : List<Movie>,
    @SerializedName("total_pages") val total_pages : String,
    @SerializedName("total_results") val total_results : String
)
