package com.example.weatherapp.general.domain

import com.example.weatherapp.general.data.weather.models.WeatherData

interface WeatherRepository {
    suspend fun getWeatherForecast(lat: Double, lon: Double): WeatherData
}
