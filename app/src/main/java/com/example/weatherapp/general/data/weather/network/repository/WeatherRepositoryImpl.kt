package com.example.weatherapp.general.data.weather.network.repository

import com.example.weatherapp.common.Result
import com.example.weatherapp.general.data.weather.network.sources.WeatherRemoteDataSource
import com.example.weatherapp.general.data.weather.models.WeatherData
import com.example.weatherapp.general.data.weather.models.WeatherRequest
import com.example.weatherapp.general.domain.WeatherRepository

class WeatherRepositoryImpl (private val weatherRemoteDataSource: WeatherRemoteDataSource) :
    WeatherRepository {

    override suspend fun getWeather(weatherRequest: WeatherRequest): Result<WeatherData> {
        return weatherRemoteDataSource.getWeather(weatherRequest = weatherRequest)
    }
}