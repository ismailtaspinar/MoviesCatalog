package com.itapps.moviescatalog.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.itapps.moviescatalog.common.Constants.API_KEY
import com.itapps.moviescatalog.common.Resource
import com.itapps.moviescatalog.data.model.Movie
import com.itapps.moviescatalog.domain.repository.MovieRepository
import com.itapps.moviescatalog.domain.source.LocalDataSource
import com.itapps.moviescatalog.domain.source.RemoteDataSource
import com.itapps.moviescatalog.utils.MoviePagingSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class MovieRepoImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
    ) : MovieRepository {

    override fun fetchMovies(query: String): Flow<PagingData<Movie>> {
        val pagingConfig = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false
        )

        val pagingSourceFactory: () -> PagingSource<Int, Movie> = {
            MoviePagingSource(remoteDataSource, API_KEY, query)
        }

        return Pager(
            config = pagingConfig,
            pagingSourceFactory = pagingSourceFactory
        ).flow
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


    override suspend fun isMovieExists(id: Int): Resource<Boolean>  {
        return withContext(Dispatchers.IO){
            Resource.Loading
            try {
                val result = localDataSource.isMovieExists(id)
                Resource.Success(result)
            }catch (e:Exception){
                Resource.Error(e)
            }
        }

    }

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
        }catch (e:Exception){
        }

    }

    override suspend fun deleteMovie(movie: Movie) {
        try {
            localDataSource.deleteMovie(movie)
            println("success")
        }catch (e:Exception){
            println(e)
        }
    }

}