package com.example.coroutineflow.presentation.gridview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutineflow.data.domain.GetItemUseCase
import com.example.coroutineflow.data.remote.model.GetItemResponse
import com.example.coroutineflow.data.remote.model.SquareItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class GridViewViewModel @Inject constructor(
    private val getItemUseCase: GetItemUseCase
): ViewModel() {
    val itemsStateFlow = MutableStateFlow<List<SquareItem>>(listOf())
    val _searchFlow = MutableStateFlow<String>("")
    val searchFlow : Flow<String> =
        _searchFlow
            .debounce(200)
            .distinctUntilChanged()

    val currentItemsFlow = itemsStateFlow.combine(searchFlow) { items, search ->
        items.filter { search.isEmpty() || it.title.contains(search) }
    }

    fun getItems() = viewModelScope.launch{
        itemsStateFlow.value =getItemUseCase(null).items

    }

    fun search(newText: String) {
        _searchFlow.value = newText
    }
}