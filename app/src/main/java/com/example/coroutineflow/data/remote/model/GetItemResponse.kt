package com.example.coroutineflow.data.remote.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class GetItemResponse(
    val items :List<SquareItem>
):BaseResponse() {
    override fun toString(): String {
        return "GetItemResponse(items=$items)"
    }
}