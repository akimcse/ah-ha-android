package com.example.ahha_android.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.ahha_android.R
import com.example.ahha_android.data.model.response.Data
import com.example.ahha_android.data.service.RetrofitBuilder
import com.example.ahha_android.data.type.Plant
import com.example.ahha_android.data.vo.SignPlantData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class EditPlantViewModel(application: Application) : AndroidViewModel(application) {
    private val _characterList = MutableLiveData<List<SignPlantData>>()
    val characterList : LiveData<List<SignPlantData>>
        get() = _characterList

    private val _plantInfo = MutableLiveData<Data>()

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

    private val token = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjEsImlhdCI6MTY0Mzk4OTg2MiwiZXhwIjoxNjc1NTI1ODYyfQ.yqv6dqYNbIPnAeLMi0-T6N7Bjf2GlcEsNJ8ysh9cWy8"

    init {
        viewModelScope.launch(Dispatchers.IO) {
            fetchPlant()
        }
    }

    private suspend fun fetchPlant() {
        try {
            val response = RetrofitBuilder.plantService.getPlantInfo(token).data
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

    // 다 자란 기본 캐릭터 데이터
    fun setCharacterList() {
        _characterList.value = mutableListOf(
            SignPlantData("브로콜리", R.drawable.ic_launcher_background),
            SignPlantData("토마토", R.drawable.ic_launcher_background),
            SignPlantData("대파", R.drawable.ic_launcher_background),
        )
    }
}