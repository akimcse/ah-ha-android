package com.example.ahha_android.data.response

data class ResponseUserData(
    val code: Int,
    val data: UserData,
    val message: String
)

data class UserData(
    val deviceId: String,
    val gmail: String,
    val id: Int,
    val notification: String,
    val notificationLimit: Int
)