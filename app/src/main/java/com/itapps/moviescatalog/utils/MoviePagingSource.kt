package com.itapps.moviescatalog.utils

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.itapps.moviescatalog.data.model.Movie
import com.itapps.moviescatalog.domain.source.RemoteDataSource

class MoviePagingSource(
    private val remoteDataSource: RemoteDataSource,
    private val apiKey: String,
    private val query: String
) : PagingSource<Int, Movie>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1
        val pageSize = params.loadSize
        return try {
            val response = remoteDataSource.fetchMovies(apiKey, query, page, pageSize)
            val movies = response.data
            val prevKey = if (page > 1) page - 1 else null
            val nextKey = if (page < response.total_pages.toInt()) page + 1 else null
            LoadResult.Page(data = movies, prevKey = prevKey, nextKey = nextKey)

        } catch (e: Exception) {
            println(e)
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        TODO("Not yet implemented")
    }
}
