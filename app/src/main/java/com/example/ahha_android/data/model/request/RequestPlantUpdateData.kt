package com.example.ahha_android.data.model.request

import com.example.ahha_android.data.model.response.Data
import com.google.gson.annotations.SerializedName

data class RequestPlantUpdateData(
    @SerializedName("name")
    val name: String,
    @SerializedName("kind")
    val kind: String
)