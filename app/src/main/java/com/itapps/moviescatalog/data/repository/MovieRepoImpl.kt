package com.itapps.moviescatalog.data.repository

import com.itapps.moviescatalog.common.Resource
import com.itapps.moviescatalog.data.model.Movie
import com.itapps.moviescatalog.data.model.MovieResponse
import com.itapps.moviescatalog.domain.repository.MovieRepository
import com.itapps.moviescatalog.domain.source.LocalDataSource
import com.itapps.moviescatalog.domain.source.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class MovieRepoImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
    ) : MovieRepository {
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

    override suspend fun getWatchListMovies(): List<Movie>  {
        return withContext(Dispatchers.IO) {
            localDataSource.getWatchListMovies()
        }
    }

    override suspend fun getFavoriteMovies(): List<Movie> {
        return withContext(Dispatchers.IO) {
            localDataSource.getFavoriteMovies()
        }
    }


    override fun isMovieExists(id: Int): Boolean = localDataSource.isMovieExists(id)

    override suspend fun getMovieById(id: Int): Movie? {
        return withContext(Dispatchers.IO) {
            localDataSource.getMovieById(id)
        }
    }

    override suspend fun updateMovie(movie: Movie) {
        localDataSource.updateMovie(movie)
    }

    override suspend fun insertMovie(movie: Movie) {
        try {
            localDataSource.insertMovie(movie)
            println("sucess")
            println(movie)
        }catch (e:Exception){
            println(e)
        }

    }

    override suspend fun deleteMovie(movie: Movie) {
        localDataSource.deleteMovie(movie)
    }

}