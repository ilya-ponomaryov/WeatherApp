package com.example.weatherapp.general.usecases.weather.models



data class WeatherForToday(
    val date: String = "00.00.00",
    val weatherIcon: String = "http://openweathermap.org/img/w/" + "04d" + ".png",
    val temperature: String = "0",
    val weatherDescription: String = "Описание",
    val feelsLike: Double = 0.0,
)
