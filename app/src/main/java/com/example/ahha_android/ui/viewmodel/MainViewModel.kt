package com.example.ahha_android.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ahha_android.data.response.PlantData
import com.example.ahha_android.data.response.UserData
import com.example.ahha_android.data.service.RetrofitBuilder
import com.example.ahha_android.data.type.Plant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MainViewModel : ViewModel() {
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

    init {
        viewModelScope.launch(Dispatchers.IO) {
            fetchUser()
            fetchMailCount()
            fetchPlant()
        }
    }

    private suspend fun fetchUser() {
        try {
            val response = RetrofitBuilder.userService.getUser(token).data
            _userInfo.postValue(response)
            _mailCountLimit.postValue(response.notificationLimit)
        } catch (e: HttpException) {
            e.printStackTrace()
        }
    }

    private suspend fun fetchMailCount() {
        try {
            val response = RetrofitBuilder.mailService.getMailCount(token).data.totalCount
            _mailCount.postValue(response)
        } catch (e: HttpException) {
            e.printStackTrace()
        }
    }

    private suspend fun fetchPlant() {
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
}