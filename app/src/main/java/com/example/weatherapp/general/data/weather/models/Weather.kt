package com.example.weatherapp.general.data.weather.models

data class Weather(
    val weatherForToday: WeatherForToday,
    val weatherForDays: List<WeatherForDay>,
)
