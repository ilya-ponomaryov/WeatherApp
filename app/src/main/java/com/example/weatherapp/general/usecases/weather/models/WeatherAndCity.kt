package com.example.weatherapp.general.usecases.weather.models

import com.example.weatherapp.general.usecases.location.City

data class WeatherAndCity(
    val weather: Weather,
    val city: City
)
