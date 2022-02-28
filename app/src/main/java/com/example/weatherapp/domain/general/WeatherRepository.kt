package com.example.weatherapp.domain.general

import com.example.weatherapp.common.Result
import com.example.weatherapp.data.models.general.WeatherData
import com.example.weatherapp.data.models.general.WeatherRequest

interface WeatherRepository {

    /*suspend fun getCurrentWeather() : Result<Current>

    suspend fun getDailyWeather() : Result<Daily>

    suspend fun getHourlyWeather() : Result<Hourly>*/

    suspend fun getWeather(weatherRequest: WeatherRequest) : Result<WeatherData>
}