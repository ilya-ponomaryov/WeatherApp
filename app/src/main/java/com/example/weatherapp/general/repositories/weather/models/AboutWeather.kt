package com.example.weatherapp.general.repositories.weather.models

data class AboutWeather(
    val description: String = "Описание",
    val icon: String = "http://openweathermap.org/img/w/" + "04d" + ".png",
    val id: Int = 0,
    val main: String = "main"
)