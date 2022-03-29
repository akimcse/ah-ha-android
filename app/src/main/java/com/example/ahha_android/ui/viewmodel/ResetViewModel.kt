package com.example.ahha_android.ui.viewmodel

import android.app.Application
import android.text.Editable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.ahha_android.data.EasyPeasySharedPreference
import com.example.ahha_android.data.model.request.RequestPlantResetData
import com.example.ahha_android.data.model.response.ResponsePlantResetData
import com.example.ahha_android.data.response.PlantData
import com.example.ahha_android.data.service.RetrofitBuilder
import com.example.ahha_android.data.type.Plant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ResetViewModel(application: Application) : AndroidViewModel(application) {
    private val _resetPlant = MutableLiveData<ResponsePlantResetData>()
    val resetPlant: LiveData<ResponsePlantResetData>
        get() = _resetPlant

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

    private val token = "Bearer ${EasyPeasySharedPreference.getAccessToken()}"

    fun fetchPlant() = viewModelScope.launch(Dispatchers.IO) {
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

    fun resetPlant(name: Editable, kind: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            _resetPlant.postValue(
                RetrofitBuilder.plantService.resetPlant(token, RequestPlantResetData(name.toString(), kind)
                )
            )
        } catch (e: HttpException) {
            e.printStackTrace()
        }
    }
}