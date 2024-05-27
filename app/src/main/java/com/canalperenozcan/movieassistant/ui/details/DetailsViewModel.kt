package com.canalperenozcan.movieassistant.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.canalperenozcan.movieassistant.common.Resource
import com.canalperenozcan.movieassistant.data.model.Movie
import com.canalperenozcan.movieassistant.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> = _movie

    private val _isExist = MutableLiveData<Resource<Boolean>>()
    val isExist : LiveData<Resource<Boolean>> get() = _isExist


    fun fetchDetails(id : String,onComplete: (Resource<Movie>) -> Unit) = viewModelScope.launch {
        movieRepository.fetchDetails(id).collect{
            onComplete(it)
        }
    }

    fun getMovieById(id : String) = viewModelScope.launch {
        _movie.value = movieRepository.getMovieById(id.toInt())
    }

    fun addMovie(movie: Movie) = viewModelScope.launch {
        movieRepository.insertMovie(movie)
    }

    fun isMovieExist(id:String) = viewModelScope.launch {
        _isExist.value = movieRepository.isMovieExists(id.toInt())
    }

    fun deleteMovie(movie: Movie) = viewModelScope.launch {
        movieRepository.deleteMovie(movie)
    }
}