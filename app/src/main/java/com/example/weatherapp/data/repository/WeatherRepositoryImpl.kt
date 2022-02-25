package com.example.weatherapp.data.repository

import com.example.weatherapp.presentation.Result
import com.example.weatherapp.data.network.source.WeatherRemoteDataSource
import com.example.weatherapp.data.models.*
import com.example.weatherapp.domain.WeatherRepository

class WeatherRepositoryImpl (private val weatherRemoteDataSource: WeatherRemoteDataSource) : WeatherRepository {

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