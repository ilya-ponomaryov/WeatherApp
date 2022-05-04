package com.example.weatherapp.general.domain

import com.example.weatherapp.general.data.weather.models.Weather
import io.reactivex.Single

interface WeatherRepository {
    fun getWeather(latitude: Double, longitude: Double): Single<Weather>
}
