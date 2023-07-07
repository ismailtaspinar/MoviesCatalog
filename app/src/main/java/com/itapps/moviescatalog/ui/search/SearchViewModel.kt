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
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    fun fetchMovies(query : String,onComplete: (Resource<MovieResponse>) -> Unit) = viewModelScope.launch {
        val response = movieRepository.fetchMovies(query).collect{
            onComplete(it)
        }
    }

    fun fetchDetails(id : String,onComplete: (Resource<Movie>) -> Unit) = viewModelScope.launch {
        val response = movieRepository.fetchDetails(id).collect{
            onComplete(it)
        }
    }
}