package com.example.weatherapp.general.domain

import com.example.weatherapp.common.Result
import com.example.weatherapp.general.data.models.WeatherData
import com.example.weatherapp.general.data.models.WeatherRequest

interface WeatherRepository {

    suspend fun getWeather(weatherRequest: WeatherRequest) : Result<WeatherData>
}