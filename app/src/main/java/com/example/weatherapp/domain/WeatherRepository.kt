package com.example.weatherapp.domain

import com.example.weatherapp.presentation.Result
import com.example.weatherapp.data.models.*

interface WeatherRepository {

    /*suspend fun getCurrentWeather() : Result<Current>

    suspend fun getDailyWeather() : Result<Daily>

    suspend fun getHourlyWeather() : Result<Hourly>*/

    suspend fun getWeather(weatherRequest: WeatherRequest) : Result<WeatherData>
}