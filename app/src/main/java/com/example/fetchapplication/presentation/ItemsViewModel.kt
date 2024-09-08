package com.example.fetchapplication.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchapplication.data.ItemsRepository
import com.example.fetchapplication.data.Result
import com.example.fetchapplication.data.model.Item
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ItemsViewModel(
    private val itemsRepository: ItemsRepository
): ViewModel() {

    private val  _items = MutableStateFlow<List<Item>>(emptyList())
    val items = _items.asStateFlow()

    private val _showErrorToastChannel = Channel<Boolean>()
    val showErrorToastChannel = _showErrorToastChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            itemsRepository.getItemsList().collectLatest { result ->
                when(result){
                    is Result.Error -> {
                        _showErrorToastChannel.send(true)
                    }
                    is Result.Success -> {
                        result.data?.let {items->
                            val filteredAndSortedItems = items
                                .filter { it.name?.isNotBlank()==true }
                                .sortedWith(compareBy<Item>{it.listId}.thenBy { it.name })

                            _items.update {filteredAndSortedItems}
                        }

                    }
                }
            }
        }
    }
}