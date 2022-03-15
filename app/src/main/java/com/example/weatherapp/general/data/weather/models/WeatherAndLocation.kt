package com.example.weatherapp.general.data.weather.models

import com.example.weatherapp.general.data.location.models.Location

data class WeatherAndLocation(
    val weatherData: WeatherData,
    val location: Location
)
