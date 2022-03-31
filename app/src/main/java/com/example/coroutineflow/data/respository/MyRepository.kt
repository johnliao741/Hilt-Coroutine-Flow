package com.example.coroutineflow.data.respository

import com.example.coroutineflow.data.remote.model.GetItemResponse
import com.example.coroutineflow.data.remote.model.SquareItem
import com.example.coroutineflow.data.service.MyService
import javax.inject.Inject

class MyRepository @Inject constructor(
    val myService: MyService
):AppRepository() {
    suspend fun getItems(): List<SquareItem> =
    callApi {
        myService.getItems()
    }

}