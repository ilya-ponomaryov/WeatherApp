package com.example.weatherapp.data.network.general.source

import com.example.weatherapp.common.Result
import com.example.weatherapp.data.models.general.WeatherData
import com.example.weatherapp.data.models.general.WeatherRequest

interface WeatherRemoteDataSource {

    suspend fun getWeather(weatherRequest: WeatherRequest) : Result<WeatherData>
}