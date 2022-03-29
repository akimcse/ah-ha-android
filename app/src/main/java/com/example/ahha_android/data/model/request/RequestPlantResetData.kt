package com.example.ahha_android.data.model.request

import com.google.gson.annotations.SerializedName

data class RequestPlantResetData(
    @SerializedName("name")
    val name: String,
    @SerializedName("kind")
    val kind: String
)
