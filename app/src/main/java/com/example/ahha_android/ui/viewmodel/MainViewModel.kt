package com.example.ahha_android.ui.viewmodel

import android.app.Application
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ahha_android.data.response.PlantData
import com.example.ahha_android.data.response.UserData
import com.example.ahha_android.data.service.RetrofitBuilder
import com.example.ahha_android.data.type.Plant
import retrofit2.HttpException

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application

    private val _userInfo = MutableLiveData<UserData>()

    private val _mailCountLimit = MutableLiveData<Int>()
    val mailCountLimit: LiveData<Int> = _mailCountLimit

    private val _mailCount = MutableLiveData<Int>()
    val mailCount: LiveData<Int> = _mailCount

    private val _plantInfo = MutableLiveData<PlantData>()

    private val _plantName = MutableLiveData<String>()
    val plantName: LiveData<String> = _plantName

    private val _plantKind = MutableLiveData<Plant>()
    val plantKind: LiveData<Plant> = _plantKind

    private val _plantLevel = MutableLiveData<Int>()
    val plantLevel: LiveData<Int> = _plantLevel

    private val _plantScore = MutableLiveData<Int>()
    val plantScore: LiveData<Int> = _plantScore

    private val _ordinalNumber = MutableLiveData<Int>()
    val ordinalNumber: LiveData<Int> = _ordinalNumber

    // FIXME
    private val token =
        "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjEsImlhdCI6MTY0Mzk4OTg2MiwiZXhwIjoxNjc1NTI1ODYyfQ.yqv6dqYNbIPnAeLMi0-T6N7Bjf2GlcEsNJ8ysh9cWy8"

    suspend fun fetchUser() {
        try {
            val response = RetrofitBuilder.userService.getUser(token).data
            _userInfo.postValue(response)
            _mailCountLimit.postValue(response.notificationLimit)
        } catch (e: HttpException) {
            e.printStackTrace()
        }
    }

    suspend fun fetchMailCount() {
        try {
            val response = RetrofitBuilder.mailService.getMailCount(token).data.totalCount
            _mailCount.postValue(response)
        } catch (e: HttpException) {
            e.printStackTrace()
        }
    }

    suspend fun fetchPlant() {
        try {
            val response = RetrofitBuilder.plantService.getPlant(token).data
            _plantInfo.postValue(response)
            _plantName.postValue(response.name)
            _plantLevel.postValue(response.level)
            _plantKind.postValue(Plant.valueOf(response.kind))
            _plantScore.postValue(response.score)
            _ordinalNumber.postValue(response.ordinalNumber)
        } catch (e: HttpException) {
            e.printStackTrace()
        }
    }

    fun goToGmail() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("https://mail.google.com")
        context.startActivity(intent.addFlags(FLAG_ACTIVITY_NEW_TASK))
    }
}