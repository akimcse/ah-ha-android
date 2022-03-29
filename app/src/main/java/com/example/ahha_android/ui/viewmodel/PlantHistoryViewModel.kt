package com.example.ahha_android.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ahha_android.data.EasyPeasySharedPreference
import com.example.ahha_android.data.response.PlantHistoryResponseData
import com.example.ahha_android.data.service.RetrofitBuilder
import com.example.ahha_android.data.vo.PlantHistoryData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class PlantHistoryViewModel : ViewModel() {
    private val _plantHistoryData = MutableLiveData<List<PlantHistoryData>>()
    val plantHistoryData: LiveData<List<PlantHistoryData>>
        get() = _plantHistoryData

    private val token = "Bearer ${EasyPeasySharedPreference.getAccessToken()}"

    init {
        viewModelScope.launch(Dispatchers.IO) {
            fetchPlantHistoryData()
        }
    }

    private suspend fun fetchPlantHistoryData() {
        try {
            val response: List<PlantHistoryResponseData> =
                RetrofitBuilder.plantHistoryService.getPlantHistory(token).data
            _plantHistoryData.postValue(response.map { PlantHistoryResponseData.from(it) })
        } catch (e: HttpException) {
            e.printStackTrace()
        }
    }
}