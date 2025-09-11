package com.example.propertylisting.model

sealed class Resource<T>(
    val data: T? = null,
    val source: DataSource? = null,
    val message: String? = null
) {
    class Success<T>(data: T, source: DataSource) : Resource<T>(data = data, source = source)
    class Error<T>(val cause: Throwable, source: DataSource) : Resource<T>(source = source)
    class Loading<T>(source: DataSource) : Resource<T>(source = source)
}
