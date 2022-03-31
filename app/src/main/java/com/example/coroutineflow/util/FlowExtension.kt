package com.example.coroutineflow.util

import android.util.Log
import com.example.coroutineflow.data.remote.model.BaseResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

object FlowExtension {
    fun <T : BaseResponse> Flow<T>.apiRetry(time: Long = 3) =
        this.retry(time) {
            it is Exception
        }.apiCheck()
    fun <T : BaseResponse> Flow<T>.apiCheck() =
        this.onEach { Log.e("retry", it.toString()) }
            .flowOn(Dispatchers.IO)
    fun <T : BaseResponse> Flow<T>.showLoading(
        action: suspend () -> Unit
    ) = this.onStart { action.invoke() }
        .flowOn(Dispatchers.Main)
}