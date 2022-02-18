package com.example.weatherapp.data

import com.example.weatherapp.Result
import com.example.weatherapp.data.models.Weather
import com.example.weatherapp.data.models.WeatherRequest

interface WeatherRemoteDataSource {

    suspend fun getWeather(weatherRequest: WeatherRequest) : Result<Weather>
}