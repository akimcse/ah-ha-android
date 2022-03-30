package com.example.ahha_android.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ahha_android.data.EasyPeasySharedPreference
import com.example.ahha_android.data.model.request.RequestNotificationUpdateData
import com.example.ahha_android.data.model.response.ResponseNotificationUpdateData
import com.example.ahha_android.data.response.UserData
import com.example.ahha_android.data.service.RetrofitBuilder
import com.example.ahha_android.data.type.Plant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class SettingViewModel : ViewModel() {
    private val _userInfo = MutableLiveData<UserData>()

    private val _notificationCount = MutableLiveData<Int>()
    val notificationCount: LiveData<Int>
        get() = _notificationCount

    private val _notificationSetting = MutableLiveData<ResponseNotificationUpdateData>()
    val notificationSetting: LiveData<ResponseNotificationUpdateData>
        get() = _notificationSetting

    private val _notificationInfo = MutableLiveData<String>()
    val notificationInfo: LiveData<String>
        get() = _notificationInfo

    private val _notificationLimit = MutableLiveData<Int>()
    val notificationLimit: LiveData<Int>
        get() = _notificationLimit

    private val _notificationMailOn = MutableLiveData<String>()
    val notificationMailOn: LiveData<String>
        get() = _notificationMailOn


    private val token = "Bearer ${EasyPeasySharedPreference.getAccessToken()}"

    init {
        initNotificationCount()
    }

    private fun initNotificationCount() {
        _notificationCount.value = 1
    }

    fun setNotificationCount(){
        _notificationCount.value = _notificationLimit.value
    }

    fun setNotificationMailOn(notificationMailOn: String){
            _notificationMailOn.value = notificationMailOn
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

    suspend fun fetchUser() {
        try {
            val response = RetrofitBuilder.userService.getUser(token).data
            _userInfo.postValue(response)
            _notificationInfo.postValue(response.notification)
            _notificationLimit.postValue(response.notificationLimit)
        } catch (e: HttpException) {
            e.printStackTrace()
        }
    }

    fun changeNotificationSetting(notificationInfo: String, notificationLimit: Int) =
        viewModelScope.launch(
            Dispatchers.IO
        ) {
            try {
                _notificationSetting.postValue(
                    RetrofitBuilder.userService.editNotification(
                        token,
                        RequestNotificationUpdateData(notificationInfo, notificationLimit)
                    )
                )
            } catch (e: HttpException) {

            }
        }
}