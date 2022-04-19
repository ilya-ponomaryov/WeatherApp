package com.example.weatherapp.general.domain

import com.example.weatherapp.general.data.weather.models.Weather

interface WeatherRepository {
    suspend fun getWeather(latitude: Double, longitude: Double): Weather
}
