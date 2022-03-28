package com.example.ahha_android

import android.app.Application
import com.example.ahha_android.data.EasyPeasySharedPreference

class EasyPeasyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initSharedPreferences()
    }

    private fun initSharedPreferences() {
        EasyPeasySharedPreference.init(applicationContext)
    }
}