package com.itapps.moviescatalog

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.itapps.moviescatalog.data.source.MovieDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MovieApplication : Application()