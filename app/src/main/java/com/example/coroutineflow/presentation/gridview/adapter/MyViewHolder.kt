package com.example.coroutineflow.presentation.gridview.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class MyViewHolder<T>(val view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(item:T)

    interface Factory<R>{
        fun create(parent:ViewGroup) : MyViewHolder<R>
    }
}