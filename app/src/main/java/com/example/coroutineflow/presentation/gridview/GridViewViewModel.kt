package com.example.coroutineflow.presentation.gridview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutineflow.data.domain.GetItemUseCase
import com.example.coroutineflow.data.remote.model.SquareItem
import com.example.coroutineflow.util.FlowExtension.apiRetry
import com.example.coroutineflow.util.FlowExtension.showLoading
import com.example.coroutineflow.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GridViewViewModel @Inject constructor(
    private val getItemUseCase: GetItemUseCase
) : ViewModel() {
    val apiStatus = MutableSharedFlow<Result<Nothing>>()
    val itemsStateFlow = MutableStateFlow<List<SquareItem>>(listOf())
    val _searchFlow = MutableStateFlow<String>("")
    val searchFlow: Flow<String> =
        _searchFlow
            .debounce(200)
            .distinctUntilChanged()

    val currentItemsFlow = itemsStateFlow.combine(searchFlow) { items, search ->
        items.filter { search.isEmpty() || it.title.contains(search) }
    }

    fun getItems() = viewModelScope.launch {
        flow {
            emit(getItemUseCase(null))
        }.showLoading {apiStatus.emit(Result.Loading)}
            .apiRetry()
            .transform {emit(it.items)}
            .catch {apiStatus.emit(Result.Error(it))}
            .onCompletion {apiStatus.emit(Result.Success)}
            .collect { itemsStateFlow.value = it }
    }

    fun search(newText: String) {
        _searchFlow.value = newText
    }
}



