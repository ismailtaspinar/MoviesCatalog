package com.itapps.moviescatalog.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.itapps.moviescatalog.data.model.Movie
import com.itapps.moviescatalog.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {

    private val _movies = MutableLiveData<PagingData<Movie>>()
    val movies: LiveData<PagingData<Movie>> get() = _movies


    fun getMovies(query: String)  = viewModelScope.launch {
          movieRepository.fetchMovies(query).cachedIn(viewModelScope).collect{
              _movies.value = it
         }
    }

}