package com.example.weatherapp.general.domain

import com.example.weatherapp.general.data.weather.models.Weather
import com.example.weatherapp.general.data.weather.network.WeatherService
import com.example.weatherapp.general.data.weather.network.repository.WeatherRepositoryImpl

interface WeatherRepository {
    suspend fun getWeather(lat: Double, lon: Double): Weather

    companion object {
        fun newRepository(service: WeatherService): WeatherRepository =
            WeatherRepositoryImpl(service)
    }
}
