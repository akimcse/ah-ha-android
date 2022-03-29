package com.example.ahha_android.data.service

import com.example.ahha_android.data.model.request.RequestPlantCreateData
import com.example.ahha_android.data.model.request.RequestPlantResetData
import com.example.ahha_android.data.model.request.RequestPlantUpdateData
import com.example.ahha_android.data.model.response.ResponsePlantCreateData
import com.example.ahha_android.data.model.response.ResponsePlantResetData
import com.example.ahha_android.data.model.response.ResponsePlantUpdateData
import retrofit2.http.*
import com.example.ahha_android.data.response.ResponsePlantData
import retrofit2.http.GET
import retrofit2.http.Header

interface PlantService {
    @POST("plant")
    suspend fun createPlant(
        @Header("Authorization") token: String,
        @Body body: RequestPlantCreateData
    ): ResponsePlantCreateData

    @PATCH("plant")
    suspend fun editPlant(
        @Header("Authorization") token: String,
        @Body body: RequestPlantUpdateData
    ): ResponsePlantUpdateData

    @GET("plant")
    suspend fun getPlant(
        @Header("Authorization") token: String
    ): ResponsePlantData

    @POST("plant/reset")
    suspend fun resetPlant(
        @Header("Authorization") token: String,
        @Body body: RequestPlantResetData
    ): ResponsePlantResetData
}