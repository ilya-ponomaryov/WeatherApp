package com.example.weatherapp.general.data.network.sources.weather

import com.example.weatherapp.common.Result
import com.example.weatherapp.general.data.models.weather.WeatherData
import com.example.weatherapp.general.data.models.weather.WeatherRequest
import com.example.weatherapp.general.data.network.WeatherService
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