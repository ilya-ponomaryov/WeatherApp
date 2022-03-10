package com.example.weatherapp.general.data.weather.network.repository

import com.example.weatherapp.general.data.weather.models.WeatherData
import com.example.weatherapp.general.data.weather.models.WeatherRequest
import com.example.weatherapp.general.data.weather.network.WeatherService
import com.example.weatherapp.general.domain.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val weatherService: WeatherService) :
    WeatherRepository {

    override suspend fun getWeather(weatherRequest: WeatherRequest) : Response<WeatherData> {
        return weatherService.getWeather(
            weatherRequest.lat,
            weatherRequest.lon,
            weatherRequest.exclude,
            weatherRequest.units,
            weatherRequest.lang,
            weatherRequest.appid)
    }
}