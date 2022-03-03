package com.example.weatherapp.general.data.network.sources.weather

import com.example.weatherapp.common.Result
import com.example.weatherapp.general.data.models.weather.WeatherData
import com.example.weatherapp.general.data.models.weather.WeatherRequest

interface WeatherRemoteDataSource {

    suspend fun getWeather(weatherRequest: WeatherRequest) : Result<WeatherData>
}