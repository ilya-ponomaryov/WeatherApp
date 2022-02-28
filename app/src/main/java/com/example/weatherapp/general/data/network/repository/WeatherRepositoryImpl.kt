package com.example.weatherapp.general.data.network.repository

import com.example.weatherapp.common.Result
import com.example.weatherapp.general.data.network.sources.WeatherRemoteDataSource
import com.example.weatherapp.general.data.models.WeatherData
import com.example.weatherapp.general.data.models.WeatherRequest
import com.example.weatherapp.general.domain.WeatherRepository

class WeatherRepositoryImpl (private val weatherRemoteDataSource: WeatherRemoteDataSource) :
    WeatherRepository {

    /*override suspend fun getCurrentWeather(): Result<Current> {

    }

    override suspend fun getDailyWeather(): Result<Daily> {

    }

    override suspend fun getHourlyWeather(): Result<Hourly> {

    }*/

    override suspend fun getWeather(weatherRequest: WeatherRequest): Result<WeatherData> {
        return weatherRemoteDataSource.getWeather(weatherRequest = weatherRequest)
    }
}