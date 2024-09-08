package com.example.fetchapplication.data

import com.example.fetchapplication.data.model.Item
import com.example.fetchapplication.data.model.Items
import retrofit2.http.GET

interface DataApi {
    @GET("hiring.json")
    suspend fun  getItemsList(): List<Item>

    companion object {
        const val BASE_URL = "https://fetch-hiring.s3.amazonaws.com/"
    }
}

