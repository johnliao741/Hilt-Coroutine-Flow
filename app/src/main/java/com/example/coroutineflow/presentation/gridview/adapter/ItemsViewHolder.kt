package com.example.coroutineflow.presentation.gridview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coroutineflow.data.remote.model.SquareItem
import com.example.coroutineflow.databinding.ItemAdapterBinding

class ItemsViewHolder(var binding:ItemAdapterBinding): MyViewHolder<SquareItem>(binding.root){
    override fun bind(_item: SquareItem) {
        binding.apply {
            itemData = _item
        }
        binding.executePendingBindings()
    }

    companion object Factory : MyViewHolder.Factory<SquareItem> {
        override fun create(parent: ViewGroup): MyViewHolder<SquareItem>
        = ItemsViewHolder(
            ItemAdapterBinding.inflate(
                LayoutInflater.from(parent.context),parent,false)
        )
        val diffUtil = object : DiffUtil.ItemCallback<SquareItem>(){
            override fun areItemsTheSame(oldItem: SquareItem, newItem: SquareItem): Boolean
                    = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: SquareItem, newItem: SquareItem): Boolean
                    = oldItem.id == newItem.id
                    && oldItem.albumId == newItem.albumId
                    && oldItem.title == newItem.title

        }
    }
}