package com.example.weatherapp.general.usecases.weather.models

import com.example.weatherapp.general.usecases.location.models.Location

data class WeatherAndLocation(
    val weather: Weather,
    val location: Location
)
