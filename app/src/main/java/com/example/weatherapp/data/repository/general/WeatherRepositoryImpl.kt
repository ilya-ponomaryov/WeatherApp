package com.example.weatherapp.data.repository.general

import com.example.weatherapp.common.Result
import com.example.weatherapp.data.network.general.source.WeatherRemoteDataSource
import com.example.weatherapp.data.models.general.WeatherData
import com.example.weatherapp.data.models.general.WeatherRequest
import com.example.weatherapp.domain.general.WeatherRepository

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