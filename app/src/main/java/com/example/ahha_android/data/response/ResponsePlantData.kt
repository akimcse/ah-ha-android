package com.example.ahha_android.data.response

data class ResponsePlantData(
    val code: Int,
    val data: PlantData,
    val message: String
)

data class PlantData(
    val id: Int,
    val kind: String,
    val level: Int,
    val name: String,
    val ordinalNumber: Int,
    val score: Int
)