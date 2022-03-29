package com.example.ahha_android.data.response

import com.example.ahha_android.data.type.Plant
import com.example.ahha_android.data.vo.PlantHistoryData
import com.example.ahha_android.util.convertDateToStringFormat

data class ResponsePlantHistoryData(
    val code: Int,
    val data: List<PlantHistoryResponseData>,
    val message: String
)

data class PlantHistoryResponseData(
    val finishTime: String,
    val id: Int,
    val kind: String,
    val name: String,
    val startTime: String
) {
    companion object {
        fun from(data: PlantHistoryResponseData): PlantHistoryData {
            val name = data.name
            val startTime = convertDateToStringFormat(data.startTime)
            val finishTime = convertDateToStringFormat(data.finishTime)
            val kind = Plant.valueOf(data.kind)
            return PlantHistoryData(name, startTime, finishTime, kind, false)
        }
    }
}