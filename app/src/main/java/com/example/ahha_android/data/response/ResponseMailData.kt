package com.example.ahha_android.data.response

data class ResponseMailData(
    val code: Int,
    val data: MailData,
    val message: String
)

data class MailData(
    val totalCount: Int
)