package com.itapps.moviescatalog.data.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id") val id : Int,
    @SerializedName("poster_path") val poster_path : String,
    @SerializedName("backdrop_path") val backdrop_path : String,
    @SerializedName("title") val title : String,
    @SerializedName("overview") val overview : String,
    @SerializedName("release_date") val release_date : String,
    @SerializedName("vote_average") val vote : Double
)
