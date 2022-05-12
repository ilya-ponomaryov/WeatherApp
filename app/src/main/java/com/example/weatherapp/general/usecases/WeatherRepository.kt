package com.example.weatherapp.general.usecases

import com.example.weatherapp.general.usecases.weather.models.Weather
import io.reactivex.Single

interface WeatherRepository {
    fun getWeather(latitude: Double, longitude: Double): Single<Weather>
}
