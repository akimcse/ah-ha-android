package com.example.ahha_android.data

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

object EasyPeasySharedPreference {
    private lateinit var preferences: SharedPreferences

    private const val PREFERENCES_NAME = "EASY_PEASY_PREFERENCES"
    private const val KEY_ACCESS_TOKEN = "KEY_ACCESS_TOKEN"
    private const val KEY_ON_BOARDING_VISIT = "KEY_ON_BOARDING_VISIT"

    fun init(context: Context) {
        preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    fun setAccessToken(token: String?) {
        preferences.edit {
            if (token == null) {
                remove(KEY_ACCESS_TOKEN)
            } else {
                putString(KEY_ACCESS_TOKEN, token)
            }
        }
    }

    fun getAccessToken(): String? = preferences.getString(KEY_ACCESS_TOKEN, null)

    fun setOnBoardingVisit(hasVisited: Boolean?) {
        preferences.edit {
            if (hasVisited == null) {
                remove(KEY_ON_BOARDING_VISIT)
            } else {
                putBoolean(KEY_ON_BOARDING_VISIT, hasVisited)
            }
        }
    }

    fun getOnBoardingVisit(): Boolean = preferences.getBoolean(KEY_ON_BOARDING_VISIT, false)
}