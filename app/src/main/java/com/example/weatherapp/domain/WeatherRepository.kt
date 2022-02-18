package com.example.weatherapp.domain

import com.example.weatherapp.data.models.Current
import com.example.weatherapp.data.models.Daily

interface WeatherRepository {

    suspend fun getCurrentWeather() : Result<Current>

    suspend fun getDailyWeather() : Result<Daily>
}