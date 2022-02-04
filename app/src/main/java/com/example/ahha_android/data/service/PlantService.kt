package com.example.ahha_android.data.service

import com.example.ahha_android.data.model.request.RequestPlantCreateData
import com.example.ahha_android.data.model.request.RequestPlantUpdateData
import com.example.ahha_android.data.model.response.ResponsePlantCreateData
import com.example.ahha_android.data.model.response.ResponsePlantData
import com.example.ahha_android.data.model.response.ResponsePlantUpdateData
import retrofit2.http.*

interface PlantService {
    @GET("plant")
    suspend fun getPlantInfo(
        @Header("Authorization") token : String
    ): ResponsePlantData

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
}