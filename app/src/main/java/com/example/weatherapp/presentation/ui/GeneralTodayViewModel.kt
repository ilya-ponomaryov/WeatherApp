package com.example.weatherapp.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.data.models.WeatherData

class GeneralTodayViewModel : ViewModel() {
    private val _weatherLiveData = MutableLiveData<WeatherData>()
    val weatherLiveData : LiveData<WeatherData>
        get() = _weatherLiveData

    fun setWeatherData(weatherData: WeatherData) {
        _weatherLiveData.value = weatherData
    }
}