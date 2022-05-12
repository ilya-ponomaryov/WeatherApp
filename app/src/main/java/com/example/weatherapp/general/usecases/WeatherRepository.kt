package com.example.weatherapp.general.usecases

import com.example.weatherapp.general.usecases.weather.models.Weather

interface WeatherRepository {
    suspend fun getWeather(latitude: Double, longitude: Double): Weather
}
