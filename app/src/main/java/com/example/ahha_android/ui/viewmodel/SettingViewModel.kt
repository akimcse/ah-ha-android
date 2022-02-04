package com.example.ahha_android.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SettingViewModel : ViewModel() {
    private val _notificationCount = MutableLiveData<Int>()
    val notificationCount: LiveData<Int>
        get() = _notificationCount

    init {
        initNotificationCount()
    }

    private fun initNotificationCount() {
        _notificationCount.value = 5
    }

    fun increaseNotificationCount() {
        _notificationCount.value = _notificationCount.value?.plus(10)
    }

    fun reduceNotificationCount() {
        if (_notificationCount.value?.compareTo(10) == -1) {
            _notificationCount.value = 0
        } else {
            _notificationCount.value = _notificationCount.value?.minus(10)
        }
    }

    fun onNotificationCountTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        if (s.isNotEmpty()) {
            _notificationCount.value = s.toString().toInt()
        }
    }
}