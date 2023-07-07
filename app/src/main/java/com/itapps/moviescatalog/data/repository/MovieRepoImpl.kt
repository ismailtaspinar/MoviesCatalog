package com.itapps.moviescatalog.data.repository

import com.itapps.moviescatalog.common.Resource
import com.itapps.moviescatalog.data.model.Movie
import com.itapps.moviescatalog.data.model.MovieResponse
import com.itapps.moviescatalog.domain.repository.MovieRepository
import com.itapps.moviescatalog.domain.source.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepoImpl(private val remoteDataSource: RemoteDataSource) : MovieRepository {
    override fun fetchMovies(query: String): Flow<Resource<MovieResponse>>  = flow {
        emit(Resource.Loading)
        try {
            val result = remoteDataSource.fetchMovies(query)
            emit(Resource.Success(result))
        }catch (e:Exception){
            emit(Resource.Error(e))
        }
    }

    override fun fetchDetails(id: String): Flow<Resource<Movie>> = flow {
        emit(Resource.Loading)
        try {
            val result = remoteDataSource.fetchDetails(id)
            emit(Resource.Success(result))
        }catch (e:Exception){
            emit(Resource.Error(e))
        }
    }

}