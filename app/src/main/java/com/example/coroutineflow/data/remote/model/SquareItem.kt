package com.example.coroutineflow.data.remote.model

class SquareItem(
    val albumId:Int,
    val id:Int,
    val title:String,
    val url:String,
    val thumbnailUrl:String
) {
    override fun toString(): String {
        return "SquareItem(albumId=$albumId, id=$id, title='$title', url='$url', thumbnailUrl='$thumbnailUrl')"
    }
}