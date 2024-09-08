package com.example.fetchapplication.data

import com.example.fetchapplication.data.model.Item
import kotlinx.coroutines.flow.Flow

interface ItemsRepository {
    suspend fun  getItemsList(): Flow<Result<List<Item>>>
}