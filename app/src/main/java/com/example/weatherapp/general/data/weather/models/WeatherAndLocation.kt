package com.example.weatherapp.general.data.weather.models

import com.example.weatherapp.general.data.location.models.Location

data class WeatherAndLocation(
    val weather: Weather,
    val location: Location
)
