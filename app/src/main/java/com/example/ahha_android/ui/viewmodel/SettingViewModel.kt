package com.example.ahha_android.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ahha_android.data.EasyPeasySharedPreference
import com.example.ahha_android.data.model.request.RequestNotificationUpdateData
import com.example.ahha_android.data.model.response.ResponseNotificationUpdateData
import com.example.ahha_android.data.service.RetrofitBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class SettingViewModel : ViewModel() {
    private val _notificationCount = MutableLiveData<Int>()
    val notificationCount: LiveData<Int>
        get() = _notificationCount

    private val _notificationSetting = MutableLiveData<ResponseNotificationUpdateData>()
    val notificationSetting: LiveData<ResponseNotificationUpdateData>
        get() = _notificationSetting

    private val token = "Bearer ${EasyPeasySharedPreference.getAccessToken()}"

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

    fun changeNotificationSetting(notification: String, notificationLimit:Int) = viewModelScope.launch(
        Dispatchers.IO) {
        try {
            _notificationSetting.postValue(RetrofitBuilder.userService.editNotification(token, RequestNotificationUpdateData(notification.toString(), notificationLimit)))
        } catch (e:HttpException){

        }
    }
}