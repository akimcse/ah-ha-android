package com.example.ahha_android.data.service

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private const val BASE_URL = "http://3.35.131.195/api/v1/"

    private val gson = GsonBuilder()
        .setLenient()
        .create()

    private val easyPeasyRetrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val userService: UserService = easyPeasyRetrofit.create(UserService::class.java)
    val plantService: PlantService = easyPeasyRetrofit.create(PlantService::class.java)
    val plantHistoryService: PlantHistoryService = easyPeasyRetrofit.create(PlantHistoryService::class.java)
    val mailService: MailService = easyPeasyRetrofit.create(MailService::class.java)
}