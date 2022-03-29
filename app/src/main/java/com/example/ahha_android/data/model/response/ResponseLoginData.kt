package com.example.ahha_android.data.model.response

data class ResponseLoginData(
    val code: Int,
    val data: LoginData,
    val message: String
)

data class LoginData(
    val accessToken: String,
    val hasPlant: Boolean
)