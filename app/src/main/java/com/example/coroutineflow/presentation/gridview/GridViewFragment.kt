package com.example.coroutineflow.presentation.gridview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import com.example.coroutineflow.R
import com.example.coroutineflow.databinding.GridViewFragmentBinding
import com.example.coroutineflow.presentation.gridview.adapter.ItemsViewHolder
import com.example.coroutineflow.presentation.gridview.adapter.MyAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class GridViewFragment : Fragment() {

    companion object {
        fun newInstance() = GridViewFragment()
    }
    private lateinit var binding : GridViewFragmentBinding
    private val gridViewViewModel: GridViewViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.grid_view_fragment, container, false)
        binding.apply {
            viewModel = gridViewViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemsAdapter = MyAdapter.createAdapter(
            viewHolder = ItemsViewHolder.Factory,
            diffUtil = ItemsViewHolder.diffUtil
        )
        with(binding.rvItems) {
            adapter = itemsAdapter
        }
        with(binding.svSearch){
            setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    gridViewViewModel.search(newText)
                    return false
                }
            })
        }
        gridViewViewModel.currentItemsFlow.asLiveData().observe(viewLifecycleOwner){
            itemsAdapter.submitList(it)
        }
        gridViewViewModel.getItems()
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}