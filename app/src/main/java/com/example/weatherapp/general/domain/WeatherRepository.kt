package com.example.weatherapp.general.domain

import com.example.weatherapp.common.Result
import com.example.weatherapp.general.data.models.WeatherData
import com.example.weatherapp.general.data.models.WeatherRequest

interface WeatherRepository {

    /*suspend fun getCurrentWeather() : Result<Current>

    suspend fun getDailyWeather() : Result<Daily>

    suspend fun getHourlyWeather() : Result<Hourly>*/

    suspend fun getWeather(weatherRequest: WeatherRequest) : Result<WeatherData>
}