package com.example.weatherapp.features.general.domain

import com.example.weatherapp.common.Result
import com.example.weatherapp.features.general.data.models.WeatherData
import com.example.weatherapp.features.general.data.models.WeatherRequest

interface WeatherRepository {

    /*suspend fun getCurrentWeather() : Result<Current>

    suspend fun getDailyWeather() : Result<Daily>

    suspend fun getHourlyWeather() : Result<Hourly>*/

    suspend fun getWeather(weatherRequest: WeatherRequest) : Result<WeatherData>
}