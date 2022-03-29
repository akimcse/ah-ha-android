package com.example.ahha_android.ui.viewmodel

import android.app.Application
import android.text.Editable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.ahha_android.R
import com.example.ahha_android.data.EasyPeasySharedPreference
import com.example.ahha_android.data.model.request.RequestLoginData
import com.example.ahha_android.data.model.request.RequestPlantCreateData
import com.example.ahha_android.data.model.response.ResponsePlantCreateData
import com.example.ahha_android.data.service.RetrofitBuilder
import com.example.ahha_android.data.vo.SignPlantData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class SignViewModel(application: Application) : AndroidViewModel(application) {
    private val _characterList = MutableLiveData<List<SignPlantData>>()
    val characterList: LiveData<List<SignPlantData>>
        get() = _characterList

    private val _newPlant = MutableLiveData<ResponsePlantCreateData>()
    val newPlant: LiveData<ResponsePlantCreateData>
        get() = _newPlant

    private val _hasPlant = MutableLiveData<Boolean>()
    val hasPlant: LiveData<Boolean>
        get() = _hasPlant

    private val _accessToken = MutableLiveData<String?>()
    val accessToken: LiveData<String?>
        get() = _accessToken

    fun loginUser(authCode: String?, pushToken: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            if (authCode == null) return@launch
            val response = RetrofitBuilder.userService.loginUser(
                RequestLoginData(
                    authorizationCode = authCode,
                    pushToken = pushToken
                )
            )
            _hasPlant.postValue(response.data.hasPlant)
            _accessToken.postValue(response.data.accessToken)
        } catch (e: HttpException) {
            e.printStackTrace()
        }
    }

    fun createPlant(name: Editable, kind: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val token = _accessToken.value
            saveUserAccessToken(token)
            _newPlant.postValue(
                RetrofitBuilder.plantService.createPlant(
                    "Bearer ${_accessToken.value}",
                    RequestPlantCreateData(name.toString(), kind)
                )
            )
        } catch (e: HttpException) {
            e.printStackTrace()
        }
    }

    // 다 자란 기본 캐릭터 데이터
    fun setCharacterList() {
        _characterList.value = mutableListOf(
            SignPlantData("대파", R.drawable.ic_green_onion_level_5),
            SignPlantData("토마토", R.drawable.ic_tomato_level_5),
            SignPlantData(
                "브로콜리", R.drawable.ic_broccoli_level_5,
            )
        )
    }

    fun setAccessToken(token: String?) {
        _accessToken.value = token
    }

    fun saveUserAccessToken(token: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            EasyPeasySharedPreference.setAccessToken(token)
        }
    }
}