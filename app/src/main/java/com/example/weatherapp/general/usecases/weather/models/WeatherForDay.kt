package com.example.weatherapp.general.usecases.weather.models


data class WeatherForDay(
    val date: String = "00.00.00",
    val weatherForHour: List<WeatherForHour> = listOf(WeatherForHour()),
    val daytimeTemperature: String = "0",
    val nighttimeTemperature: String = "0",
    val weatherIcon: String = "http://openweathermap.org/img/w/" + "04d" + ".png",
)
