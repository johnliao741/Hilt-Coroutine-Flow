package com.example.coroutineflow.util

sealed class Result<T>{
    class Error(e:Throwable) : Result<Throwable>()
    class Success<R>(val data:R):Result<R>()
    object Loading : Result<Nothing>()
}
