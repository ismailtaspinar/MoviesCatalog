package com.itapps.moviescatalog.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itapps.moviescatalog.common.Resource
import com.itapps.moviescatalog.data.model.Movie
import com.itapps.moviescatalog.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> = _movie


    fun fetchDetails(id : String,onComplete: (Resource<Movie>) -> Unit) = viewModelScope.launch {
        val response = movieRepository.fetchDetails(id).collect{
            onComplete(it)
        }
    }

    fun getMovieById(id : String) = viewModelScope.launch {
        _movie.value = movieRepository.getMovieById(id.toInt())
    }

    fun addMovie(movie: Movie) = viewModelScope.launch {
        movieRepository.insertMovie(movie)
    }
}