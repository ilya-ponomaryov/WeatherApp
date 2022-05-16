package com.example.weatherapp.general.usecases.weather.models

data class Weather(
    val weatherForToday: WeatherForToday,
    val weatherForDays: List<WeatherForDay>,
)
