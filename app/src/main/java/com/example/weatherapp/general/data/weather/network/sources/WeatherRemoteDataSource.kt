package com.example.weatherapp.general.data.weather.network.sources

import com.example.weatherapp.common.Result
import com.example.weatherapp.general.data.weather.models.WeatherData
import com.example.weatherapp.general.data.weather.models.WeatherRequest

interface WeatherRemoteDataSource {

    suspend fun getWeather(weatherRequest: WeatherRequest) : Result<WeatherData>
}