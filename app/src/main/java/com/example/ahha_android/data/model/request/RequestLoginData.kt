package com.example.ahha_android.data.model.request

data class RequestLoginData(
    val authorizationCode: String,
    val pushToken: String
)