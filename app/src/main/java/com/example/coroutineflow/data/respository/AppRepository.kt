package com.example.coroutineflow.data.respository

import android.util.Log
import com.example.coroutineflow.data.remote.model.BaseResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class AppRepository {
    suspend fun <T : Any> callApi(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        action: suspend () -> T
    ) = withContext(dispatcher) {
        val response = action.invoke()
        response
    }
}