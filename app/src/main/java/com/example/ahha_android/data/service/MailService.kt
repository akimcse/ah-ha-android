package com.example.ahha_android.data.service

import com.example.ahha_android.data.response.ResponseMailData
import retrofit2.http.GET
import retrofit2.http.Header

interface MailService {
    @GET("mail/count")
    suspend fun getMailCount(
        @Header("Authorization") token: String
    ): ResponseMailData
}