package com.example.coroutineflow.data.domain

import android.util.Log
import com.example.coroutineflow.data.remote.model.GetItemResponse
import com.example.coroutineflow.data.respository.MyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.annotation.Nullable
import javax.inject.Inject
import kotlin.Exception

class GetItemUseCase @Inject constructor(
    val myRepository: MyRepository
) : AppUseCase<Void?, GetItemResponse>() {
    override suspend operator fun invoke(@Nullable request: Void?): GetItemResponse =
        withContext(Dispatchers.IO) {
            delay(500)
            //throw Exception("API Error")
            GetItemResponse(items = myRepository.getItems())
        }

}