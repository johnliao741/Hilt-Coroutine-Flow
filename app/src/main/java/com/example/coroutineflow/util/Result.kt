package com.example.coroutineflow.util

sealed class Result<out R>{
    class Error(val e:Throwable) : Result<Nothing>()
    object Success : Result<Nothing>()
    object Loading : Result<Nothing>()
}
