package com.example.fetchapplication.data

import com.example.fetchapplication.data.model.Item
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException

class ItemsRepositoryImpl(
    private val api: DataApi
) : ItemsRepository {

    override suspend fun getItemsList(): Flow<Result<List<Item>>> {
        return flow {
            try {
                val itemsFromApi = api.getItemsList() //  return List<Item>
                emit(Result.Success(itemsFromApi))
            } catch (e: IOException) {
                emit(Result.Error(message = "Error loading Items"))
                return@flow
            } catch (e: HttpException) {
                emit(Result.Error(message = "Error loading Items"))
                return@flow
            } catch (e: Exception) {
                emit(Result.Error(message = "Error loading Items"))
                return@flow
            }

        }
    }
}


