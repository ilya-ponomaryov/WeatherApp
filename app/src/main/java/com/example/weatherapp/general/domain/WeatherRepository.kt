package com.example.weatherapp.general.domain

import com.example.weatherapp.general.data.weather.models.WeatherCollection
import com.example.weatherapp.general.data.weather.models.WeatherData
import com.example.weatherapp.general.data.weather.network.WeatherService
import com.example.weatherapp.general.data.weather.network.repository.WeatherRepositoryImpl

interface WeatherRepository {
    suspend fun getWeather(lat: Double, lon: Double): WeatherCollection

    companion object {
        fun newRepository(service: WeatherService): WeatherRepository =
            WeatherRepositoryImpl(service)
    }
}
