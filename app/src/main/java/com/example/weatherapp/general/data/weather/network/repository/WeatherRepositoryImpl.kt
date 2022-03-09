package com.example.weatherapp.general.data.weather.network.repository

import com.example.weatherapp.common.Result
import com.example.weatherapp.general.data.weather.models.WeatherData
import com.example.weatherapp.general.data.weather.models.WeatherRequest
import com.example.weatherapp.general.data.weather.network.WeatherService
import com.example.weatherapp.general.domain.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val weatherService: WeatherService) :
    WeatherRepository {

    override suspend fun getWeather(weatherRequest: WeatherRequest): Result<WeatherData>
            = withContext(Dispatchers.IO) {
        try {
            val response = weatherService.getWeather(
                weatherRequest.lat,
                weatherRequest.lon,
                weatherRequest.exclude,
                weatherRequest.units,
                weatherRequest.lang,
                weatherRequest.appid)
            if (response.isSuccessful) {
                return@withContext Result.Success(response.body()!!)
            } else {
                return@withContext Result.Error(Exception(response.message()))
            }
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }

    }
}