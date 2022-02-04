package com.example.ahha_android.data.model.response

import com.google.gson.annotations.SerializedName

data class ResponsePlantUpdateData(
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: String
)