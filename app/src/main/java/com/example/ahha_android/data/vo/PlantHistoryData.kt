package com.example.ahha_android.data.vo

import com.example.ahha_android.data.type.Plant

data class PlantHistoryData(
    val name: String,
    val startTime: String,
    val finishTime: String,
    val kind: Plant,
    val hasExchanged: Boolean
)