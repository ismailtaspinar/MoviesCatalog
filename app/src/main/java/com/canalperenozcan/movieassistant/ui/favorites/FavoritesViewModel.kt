package com.canalperenozcan.movieassistant.ui.favorites


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.canalperenozcan.movieassistant.data.model.Movie
import com.canalperenozcan.movieassistant.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    fun getFavoriteMovies() = viewModelScope.launch {
        _movies.value = movieRepository.getFavoriteMovies()
    }
}