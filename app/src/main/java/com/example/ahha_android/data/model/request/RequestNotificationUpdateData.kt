package com.example.ahha_android.data.model.request

import com.google.gson.annotations.SerializedName

data class RequestNotificationUpdateData(
    @SerializedName("notification")
    val notification: String,
    @SerializedName("notificationLimit")
    val notificationLimit: Int
)
