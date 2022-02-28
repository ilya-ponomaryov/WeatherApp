package com.example.weatherapp.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharingViewModel :  ViewModel() {
    private val _shareLiveData = MutableLiveData<Any>()
    val shareLiveData: LiveData<Any>
    get() = _shareLiveData

    fun setShareLiveData(data: Any) {
        _shareLiveData.value = data
    }
}