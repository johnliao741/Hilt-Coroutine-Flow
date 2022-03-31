package com.example.coroutineflow.presentation.gridview

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import com.example.coroutineflow.R
import com.example.coroutineflow.databinding.GridViewFragmentBinding
import com.example.coroutineflow.databinding.LoadingDialogBinding
import com.example.coroutineflow.presentation.gridview.adapter.ItemsViewHolder
import com.example.coroutineflow.presentation.gridview.adapter.MyAdapter
import com.example.coroutineflow.util.Result
import com.google.android.material.dialog.MaterialAlertDialogBuilder
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
    private lateinit var progressDialog : AlertDialog
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.grid_view_fragment, container, false)
        binding.apply {
            viewModel = gridViewViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        progressDialog = requireContext().createLoadingDialog()
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
        gridViewViewModel.apiStatus.asLiveData().observe(viewLifecycleOwner){
            when(it){
                is Result.Loading -> {
                    Log.e("show","show")
                    progressDialog.show()
                }
                is Result.Success -> {
                    progressDialog.dismiss()
                }
                is Result.Error -> {
                    context?.showToast(it.e.message ?: "發生錯誤")
                }
            }
        }
        gridViewViewModel.getItems()
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}
fun Context.showToast(msg : String) =
    Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()

fun Context.createLoadingDialog():AlertDialog =
    MaterialAlertDialogBuilder(this)
        .setView(
            LoadingDialogBinding.inflate(LayoutInflater.from(this)).root
        )
        .setCancelable(false)
        .create()
