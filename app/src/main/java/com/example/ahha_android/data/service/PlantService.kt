package com.example.ahha_android.data.service

import com.example.ahha_android.data.response.ResponsePlantData
import retrofit2.http.GET
import retrofit2.http.Header

interface PlantService {
    @GET("plant")
    suspend fun getPlant(
        @Header("Authorization") token: String
    ): ResponsePlantData
}