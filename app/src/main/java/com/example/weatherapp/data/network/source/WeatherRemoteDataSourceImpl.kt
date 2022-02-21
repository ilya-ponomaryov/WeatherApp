package com.example.weatherapp.data.network.source

import com.example.weatherapp.presentation.Result
import com.example.weatherapp.data.models.WeatherData
import com.example.weatherapp.data.models.WeatherRequest
import com.example.weatherapp.data.network.WeatherService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherRemoteDataSourceImpl @Inject constructor(
    private val weatherService: WeatherService
) : WeatherRemoteDataSource {

    override suspend fun getWeather(weatherRequest: WeatherRequest): Result<WeatherData>
    = withContext(Dispatchers.IO) {
        try {
            val response = weatherService.getWeather(
                weatherRequest.lat,
                weatherRequest.lon,
                weatherRequest.exclude,
                weatherRequest.units,
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