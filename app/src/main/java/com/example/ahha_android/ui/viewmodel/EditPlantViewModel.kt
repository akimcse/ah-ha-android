package com.example.ahha_android.ui.viewmodel

import android.app.Application
import android.text.Editable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.ahha_android.R
import com.example.ahha_android.data.EasyPeasySharedPreference
import com.example.ahha_android.data.model.request.RequestPlantUpdateData
import com.example.ahha_android.data.model.response.ResponsePlantUpdateData
import com.example.ahha_android.data.response.PlantData
import com.example.ahha_android.data.service.RetrofitBuilder
import com.example.ahha_android.data.type.Plant
import com.example.ahha_android.data.vo.SignPlantData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class EditPlantViewModel(application: Application) : AndroidViewModel(application) {
    private val _characterList = MutableLiveData<List<SignPlantData>>()
    val characterList: LiveData<List<SignPlantData>>
        get() = _characterList

    private val _newPlant = MutableLiveData<ResponsePlantUpdateData>()
    val newPlant: LiveData<ResponsePlantUpdateData>
        get() = _newPlant

    private val _plantInfo = MutableLiveData<PlantData>()

    private val _plantName = MutableLiveData<String>()
    val plantName: LiveData<String> = _plantName

    private val _plantKind = MutableLiveData<Plant>()
    val plantKind: LiveData<Plant> = _plantKind

    private val _plantLevel = MutableLiveData<Int>()
    val plantLevel: LiveData<Int> = _plantLevel

    private val _plantScore = MutableLiveData<Int>()
    val plantScore: LiveData<Int> = _plantScore

    private val _ordinalNumber = MutableLiveData<Int>()
    val ordinalNumber: LiveData<Int> = _ordinalNumber

    private val token = "Bearer ${EasyPeasySharedPreference.getAccessToken()}"

    fun fetchPlant() = viewModelScope.launch(Dispatchers.IO) {
        try {
            val response = RetrofitBuilder.plantService.getPlant(token).data
            _plantInfo.postValue(response)
            _plantName.postValue(response.name)
            _plantLevel.postValue(response.level)
            _plantKind.postValue(Plant.valueOf(response.kind))
            _plantScore.postValue(response.score)
            _ordinalNumber.postValue(response.ordinalNumber)
        } catch (e: HttpException) {
            e.printStackTrace()
        }
    }

    fun changePlantInfo(name: Editable, kind: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            _newPlant.postValue(
                RetrofitBuilder.plantService.editPlant(
                    token,
                    RequestPlantUpdateData(name.toString(), kind)
                )
            )
        } catch (e: HttpException) {
        }
    }

    // 다 자란 기본 캐릭터 데이터
    fun setCharacterList() {
        _characterList.value = mutableListOf(
            SignPlantData("대파", R.drawable.ic_green_onion_level_5),
            SignPlantData("토마토", R.drawable.ic_tomato_level_5),
            SignPlantData("브로콜리", R.drawable.ic_broccoli_level_5)
        )
    }
}