package com.example.ahha_android.data.service

import com.example.ahha_android.data.response.ResponseUserData
import retrofit2.http.GET
import retrofit2.http.Header

interface UserService {
    @GET("user/me")
    suspend fun getUser(
        @Header("Authorization") token: String
    ): ResponseUserData
}