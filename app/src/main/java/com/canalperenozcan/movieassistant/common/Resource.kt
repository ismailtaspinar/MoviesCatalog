package com.canalperenozcan.movieassistant.common

sealed class Resource<out T : Any> {
    object Loading : Resource<Nothing>()
    data class Success<out T : Any>(val response: Any) : Resource<T>()
    data class Error(val exception: Exception) : Resource<Nothing>()
}
