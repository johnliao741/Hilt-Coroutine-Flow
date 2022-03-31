package com.example.coroutineflow.data.domain

abstract class AppUseCase<T,R> {
    abstract suspend fun invoke(request:T):R
}