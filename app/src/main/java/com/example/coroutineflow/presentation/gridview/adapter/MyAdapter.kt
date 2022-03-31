package com.example.coroutineflow.presentation.gridview.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter



abstract class MyAdapter<T>(diffUtil: DiffUtil.ItemCallback<T>) :
    ListAdapter<T, MyViewHolder<T>>(diffUtil) {
    abstract var typeMaps: Map<Int, MyViewHolder.Factory<T>>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder<T> {
        return typeMaps[viewType]?.create(parent) ?: throw Exception("View holder error")
    }

    override fun onBindViewHolder(holder:MyViewHolder<T>, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int = defaultType

    companion object  {
        const val defaultType = 0
        fun <T> createAdapter(
            viewHolder: MyViewHolder.Factory<T>,
            diffUtil: DiffUtil.ItemCallback<T>
        ): ListAdapter<T, MyViewHolder<T>> =
            object : MyAdapter<T>(diffUtil) {
                override var typeMaps: Map<Int, MyViewHolder.Factory<T>> =
                    mutableMapOf<Int, MyViewHolder.Factory<T>>().plus(Pair(defaultType, viewHolder))
            }
    }
}