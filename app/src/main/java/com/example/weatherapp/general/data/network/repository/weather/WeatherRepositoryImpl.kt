package com.example.weatherapp.general.data.network.repository.weather

import com.example.weatherapp.common.Result
import com.example.weatherapp.general.data.network.sources.weather.WeatherRemoteDataSource
import com.example.weatherapp.general.data.models.weather.WeatherData
import com.example.weatherapp.general.data.models.weather.WeatherRequest
import com.example.weatherapp.general.domain.WeatherRepository

class WeatherRepositoryImpl (private val weatherRemoteDataSource: WeatherRemoteDataSource) :
    WeatherRepository {

    override suspend fun getWeather(weatherRequest: WeatherRequest): Result<WeatherData> {
        return weatherRemoteDataSource.getWeather(weatherRequest = weatherRequest)
    }
}