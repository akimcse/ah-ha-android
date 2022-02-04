package com.example.ahha_android.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.ahha_android.R
import com.example.ahha_android.data.model.response.ResponsePlantData
import com.example.ahha_android.data.service.RetrofitBuilder
import com.example.ahha_android.data.vo.SignPlantData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class SignViewModel(application: Application) : AndroidViewModel(application) {
    private val _characterList = MutableLiveData<List<SignPlantData>>()
    val characterList : LiveData<List<SignPlantData>>
        get() = _characterList

    // 다 자란 기본 캐릭터 데이터
    fun setCharacterList() {
        _characterList.value = mutableListOf(
            SignPlantData("브로콜리", R.drawable.ic_launcher_background),
            SignPlantData("토마토", R.drawable.ic_launcher_background),
            SignPlantData("대파", R.drawable.ic_launcher_background),
        )
    }
}