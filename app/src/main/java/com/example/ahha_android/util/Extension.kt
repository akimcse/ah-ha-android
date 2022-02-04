package com.example.ahha_android.util

import java.text.SimpleDateFormat

fun convertDateToStringFormat(date: String): String {
    val inputDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    val inputDate = inputDateFormat.parse(date)
    val dateFormat = SimpleDateFormat("yyyy.MM.dd")
    return dateFormat.format(inputDate)
}