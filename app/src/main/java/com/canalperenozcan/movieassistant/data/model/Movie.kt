package com.canalperenozcan.movieassistant.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie")
data class Movie(
    @SerializedName("id") @PrimaryKey val id : String,
    @SerializedName("poster_path") val poster_path : String,
    @SerializedName("backdrop_path") val backdrop_path : String,
    @SerializedName("title") val title : String,
    @SerializedName("overview") val overview : String,
    @SerializedName("release_date") val release_date : String,
    @SerializedName("vote_average") val vote : String,
    var isWatchList : Boolean = false,
    var isFavorite : Boolean = false
)
