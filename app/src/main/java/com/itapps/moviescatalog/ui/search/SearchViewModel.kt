package com.itapps.moviescatalog.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itapps.moviescatalog.common.Resource
import com.itapps.moviescatalog.data.model.Movie
import com.itapps.moviescatalog.data.model.MovieResponse
import com.itapps.moviescatalog.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {


    fun fetchMovies(query : String,onComplete: (Resource<MovieResponse>) -> Unit) = viewModelScope.launch {
        val response = movieRepository.fetchMovies(query).collect{
            onComplete(it)
        }
    }

    fun isMovieExist(id:String) : Boolean = movieRepository.isMovieExists(id.toInt())

    fun addMovie(movie: Movie) = viewModelScope.launch {
        movieRepository.insertMovie(movie)
    }

    fun updateMovie(movie : Movie) = viewModelScope.launch {
        movieRepository.updateMovie(movie)
    }
}