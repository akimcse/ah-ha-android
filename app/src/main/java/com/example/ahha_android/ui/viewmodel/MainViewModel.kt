package com.example.ahha_android.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _mailCountLimit = MutableLiveData<Int>()
    val mailCountLimit: LiveData<Int>
        get() = _mailCountLimit

    private val _mailCount = MutableLiveData<Int>()
    val mailCount: LiveData<Int>
        get() = _mailCount

    init {
        initMailCountLimit()
        initMailCount()
    }

    private fun initMailCountLimit() {
        _mailCountLimit.value = 50
    }

    private fun initMailCount() {
        _mailCount.value = 40
    }
}