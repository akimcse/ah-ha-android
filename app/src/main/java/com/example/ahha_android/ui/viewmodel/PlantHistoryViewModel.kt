package com.example.ahha_android.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ahha_android.data.vo.PlantHistoryData

class PlantHistoryViewModel : ViewModel() {
    private val _plantHistoryData = MutableLiveData<List<PlantHistoryData>>()
    val plantHistoryData: LiveData<List<PlantHistoryData>>
        get() = _plantHistoryData

    init {
        fetchPlantHistoryData()
    }

    private fun fetchPlantHistoryData() {
        _plantHistoryData.value = listOf(
            PlantHistoryData("토마토", "2021.12.08", "2022.01.31"),
            PlantHistoryData("감자", "2021.12.08", "2022.01.31"),
            PlantHistoryData("양파", "2021.12.08", "2022.01.31"),
            PlantHistoryData("브로콜리", "2021.12.08", "2022.01.31"),
            PlantHistoryData("오이", "2021.12.08", "2022.01.31"),
        )
    }
}