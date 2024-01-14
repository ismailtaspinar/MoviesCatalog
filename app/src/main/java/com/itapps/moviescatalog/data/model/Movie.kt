package com.itapps.moviescatalog.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken

@Entity(tableName = "movie")
data class Movie(
    @SerializedName("id") @PrimaryKey val id : String,
    @SerializedName("poster_path") val poster_path : String,
    @SerializedName("backdrop_path") val backdrop_path : String,
    @SerializedName("title") val title : String,
    @SerializedName("overview") val overview : String,
    @SerializedName("release_date") val release_date : String,
    @SerializedName("vote_average") val vote : String,
    @SerializedName("genre_ids") val genre_ids : List<Int>?,
    var isWatchList : Boolean = false,
    var isFavorite : Boolean = false
)


class Converters {
    @TypeConverter
    fun fromGenreIdList(genreIds: List<Int>?): String? {
        if (genreIds == null) return null
        return Gson().toJson(genreIds)
    }

    @TypeConverter
    fun toGenreIdList(genreIdString: String?): List<Int>? {
        if (genreIdString == null) return null
        val type = object : TypeToken<List<Int>>() {}.type
        return Gson().fromJson(genreIdString, type)
    }
}



