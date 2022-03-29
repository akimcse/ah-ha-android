package com.example.ahha_android.data.service

import com.example.ahha_android.data.model.request.RequestLoginData
import com.example.ahha_android.data.model.response.ResponseLoginData
import com.example.ahha_android.data.response.ResponseUserData
import retrofit2.http.*

interface UserService {
    @GET("user/me")
    suspend fun getUser(
        @Header("Authorization") token: String
    ): ResponseUserData

    @POST("auth/google")
    suspend fun loginUser(
        @Body body: RequestLoginData
    ): ResponseLoginData
}