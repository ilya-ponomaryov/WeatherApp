package com.example.weatherapp.data.network.source

import com.example.weatherapp.presentation.Result
import com.example.weatherapp.data.models.WeatherData
import com.example.weatherapp.data.models.WeatherRequest

interface WeatherRemoteDataSource {

    suspend fun getWeather(weatherRequest: WeatherRequest) : Result<WeatherData>
}