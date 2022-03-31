package com.example.coroutineflow.data.service

import com.example.coroutineflow.data.remote.model.GetItemResponse
import com.example.coroutineflow.data.remote.model.SquareItem
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.http.GET

interface MyService {
    @GET("photos")
    suspend fun getItems():List<SquareItem>
}