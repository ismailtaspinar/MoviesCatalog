package com.itapps.moviescatalog.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itapps.moviescatalog.common.Resource
import com.itapps.moviescatalog.data.model.Movie
import com.itapps.moviescatalog.data.model.MovieResponse
import com.itapps.moviescatalog.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
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

    private val _recommendedMovies = MutableLiveData<Resource<MovieResponse>>()
    val recommendedMovies : LiveData<Resource<MovieResponse>> get() = _recommendedMovies


    var recommendedMovieList = MutableLiveData<List<Movie>>(emptyList())


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

    fun getRecommendations(id : String) = viewModelScope.launch {
        movieRepository.getRecommendations(id).collect{
            _recommendedMovies.postValue(it)
        }

    }
}