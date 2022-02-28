package com.example.weatherapp.features.general.data.network.sources

import com.example.weatherapp.common.Result
import com.example.weatherapp.features.general.data.models.WeatherData
import com.example.weatherapp.features.general.data.models.WeatherRequest

interface WeatherRemoteDataSource {

    suspend fun getWeather(weatherRequest: WeatherRequest) : Result<WeatherData>
}