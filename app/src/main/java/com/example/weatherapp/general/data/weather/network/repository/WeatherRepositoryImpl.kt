package com.example.weatherapp.general.data.weather.network.repository

import com.example.weatherapp.general.data.weather.models.WeatherData
import com.example.weatherapp.general.data.weather.models.WeatherRequest
import com.example.weatherapp.general.data.weather.network.WeatherService
import com.example.weatherapp.general.domain.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val weatherService: WeatherService) :
    WeatherRepository {

    override suspend fun getWeather(weatherRequest: WeatherRequest) : WeatherData
        = withContext(Dispatchers.IO) {
         val result = weatherService.getWeather(
            weatherRequest.lat,
            weatherRequest.lon,
            weatherRequest.exclude,
            weatherRequest.units,
            weatherRequest.lang,
            weatherRequest.appid
        )
        if (result.isSuccessful) {
            return@withContext result.body()!!
        } else {
            throw Exception("Error")
        }
    }

}