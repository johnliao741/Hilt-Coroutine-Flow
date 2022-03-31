package com.example.coroutineflow.data.domain

import android.util.Log
import com.example.coroutineflow.data.remote.model.GetItemResponse
import com.example.coroutineflow.data.respository.MyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.annotation.Nullable
import javax.inject.Inject

class GetItemUseCase @Inject constructor(
    val myRepository: MyRepository
) : AppUseCase<Void?, GetItemResponse>() {
    override suspend operator fun invoke(@Nullable request: Void?): GetItemResponse =
        withContext(Dispatchers.IO) {
            GetItemResponse(items = myRepository.getItems())
        }

}