package com.example.weatherapp.general.domain

import com.example.weatherapp.general.data.weather.models.WeatherData
import com.example.weatherapp.general.data.weather.models.WeatherRequest
import retrofit2.Response

interface WeatherRepository {

    suspend fun getWeather(weatherRequest: WeatherRequest) : Response<WeatherData>
}