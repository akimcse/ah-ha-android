package com.example.ahha_android.data.service

import com.example.ahha_android.data.response.ResponsePlantHistoryData
import retrofit2.http.GET
import retrofit2.http.Header

interface PlantHistoryService {
    @GET("plant-history")
    suspend fun getPlantHistory(
        @Header("Authorization") token: String
    ): ResponsePlantHistoryData
}