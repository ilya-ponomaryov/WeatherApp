package com.example.weatherapp.general.usecases.weather.models


data class WeatherForHour(
    val hour: String = "0",
    val weatherIcon: String = "http://openweathermap.org/img/w/" + "04d" + ".png",
    val temperature: String = "0",
)