package com.example.weatherapp.general.usecases.weather.models



data class WeatherForToday(
    val date: String = "00.00.00",
    val feelsLike: Double = 0.0,
    val temperature: String = "0",
    val weatherIcon: String = "http://openweathermap.org/img/w/" + "04d" + ".png",
    val weatherDescription: String = "Описание",
)
