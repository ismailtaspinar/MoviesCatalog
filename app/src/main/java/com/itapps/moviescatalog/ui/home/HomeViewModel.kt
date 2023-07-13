package com.itapps.moviescatalog.ui.home

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
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _playingMovies = MutableLiveData<Resource<MovieResponse>>()
    val playingMovies: LiveData<Resource<MovieResponse>> get() = _playingMovies

    private val _upcomingMovies = MutableLiveData<Resource<MovieResponse>>()
    val upcomingMovies: LiveData<Resource<MovieResponse>> get() = _upcomingMovies

    private val _topMovies = MutableLiveData<Resource<MovieResponse>>()
    val topMovies: LiveData<Resource<MovieResponse>> get() = _topMovies

    fun getPlayingMovies() = viewModelScope.launch {
        movieRepository.fetchPlayingMovies().collect{
            _playingMovies.value = it
        }
    }

    fun getUpcomingMovies() = viewModelScope.launch {
        movieRepository.fetchUpcomingMovies().collect{
            _upcomingMovies.value = it
        }
    }

    fun getTopMovies() = viewModelScope.launch {
        movieRepository.fetchTopMovies().collect{
            _topMovies.value = it
        }
    }
}