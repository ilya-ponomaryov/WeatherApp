package com.example.weatherapp.general.data.weather.models

data class WeatherCollection(
    val weatherForToday: WeatherForToday,
    val weatherForDay: List<WeatherForDay>,
)
