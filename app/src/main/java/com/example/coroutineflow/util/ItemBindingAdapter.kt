package com.example.coroutineflow.util

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders

object ItemBindingAdapter {
    @JvmStatic
    @BindingAdapter("setThumbnail","setImage")
    fun loadImage(view: ImageView,thumbnailUrl:String?,imageUrl:String?){
        if(thumbnailUrl == null || imageUrl == null)return
        if(!imageUrl.isNullOrEmpty()){
            val thumbnail = Glide.with(view.context).load(thumbnailUrl.getGlideUrl()).override(50,50)
            Glide.with(view.context).load(imageUrl.getGlideUrl()).thumbnail(thumbnail).override(150,150).into(view)
        }

    }
    @JvmStatic
    @BindingAdapter("text")
    fun setText(view: TextView,i:Int){
        view.text = "$i"
    }

    fun String.getGlideUrl():GlideUrl = GlideUrl(this,LazyHeaders.Builder().build())
}