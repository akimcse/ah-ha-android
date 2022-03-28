package com.example.ahha_android.util

import android.os.Build
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.example.ahha_android.R
import java.text.SimpleDateFormat

fun convertDateToStringFormat(date: String): String {
    val inputDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    val inputDate = inputDateFormat.parse(date)
    val dateFormat = SimpleDateFormat("yyyy.MM.dd")
    return dateFormat.format(inputDate)
}

fun setStatusBarColor(activity: FragmentActivity, colorRes: Int) {
    activity.window.apply {
        addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        statusBarColor = ContextCompat.getColor(activity, colorRes)
        if (colorRes == R.color.white && Build.VERSION.SDK_INT >= 23) {
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }
}