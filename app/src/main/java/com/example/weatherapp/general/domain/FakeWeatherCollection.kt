package com.example.weatherapp.general.domain

import com.example.weatherapp.general.data.weather.models.*

fun getFakeWeatherForToday(): WeatherForToday {
    return WeatherForToday(
        0,
        0.0,
        "00.00.00",
        0.0,
        0,
        0,
        0,
        0,
        "0",
        0.0,
        0,
        getWeatherList(),
        0,
        0.0,
        0.0,
        "http://openweathermap.org/img/w/" + "04d" + ".png",
        "Описание",
    )
}

private fun getWeatherList(): List<AboutWeather> {
    return listOf<AboutWeather>(
        AboutWeather(
            description = "Описание",
            icon = "04d",
            id = 0,
            main = "main"
        )
    )
}

 fun getFakeWeatherForDay(): List<WeatherForDay> {
    val feelsLike = FeelsLike(
        day = 0.0,
        eve = 0.0,
        morn = 0.0,
        night = 0.0
    )

    return listOf(
        WeatherForDay(
            0,
            0.0,
            "00.00.00",
            feelsLike,
            0,
            0.0,
            0,
            0,
            0.0,
            0,
            0.0,
            0.0,
            0,
            0,
            0.0,
            getWeatherList(),
            0,
            0.0,
            0.0,
            getWeatherForHour(),
            "0",
            "0",
            "0",
            "0",
            "0",
            "0",
            "http://openweathermap.org/img/w/" + "04d" + ".png",
        ),
        WeatherForDay(
            0,
            0.0,
            "00.00.00",
            feelsLike,
            0,
            0.0,
            0,
            0,
            0.0,
            0,
            0.0,
            0.0,
            0,
            0,
            0.0,
            getWeatherList(),
            0,
            0.0,
            0.0,
            getWeatherForHour(),
            "0",
            "0",
            "0",
            "0",
            "0",
            "0",
            "http://openweathermap.org/img/w/" + "04d" + ".png",
        ),
        WeatherForDay(
            0,
            0.0,
            "00.00.00",
            feelsLike,
            0,
            0.0,
            0,
            0,
            0.0,
            0,
            0.0,
            0.0,
            0,
            0,
            0.0,
            getWeatherList(),
            0,
            0.0,
            0.0,
            getWeatherForHour(),
            "0",
            "0",
            "0",
            "0",
            "0",
            "0",
            "http://openweathermap.org/img/w/" + "04d" + ".png",
        ),
        WeatherForDay(
            0,
            0.0,
            "00.00.00",
            feelsLike,
            0,
            0.0,
            0,
            0,
            0.0,
            0,
            0.0,
            0.0,
            0,
            0,
            0.0,
            getWeatherList(),
            0,
            0.0,
            0.0,
            getWeatherForHour(),
            "0",
            "0",
            "0",
            "0",
            "0",
            "0",
            "http://openweathermap.org/img/w/" + "04d" + ".png",
        ),
        WeatherForDay(
            0,
            0.0,
            "00.00.00",
            feelsLike,
            0,
            0.0,
            0,
            0,
            0.0,
            0,
            0.0,
            0.0,
            0,
            0,
            0.0,
            getWeatherList(),
            0,
            0.0,
            0.0,
            getWeatherForHour(),
            "0",
            "0",
            "0",
            "0",
            "0",
            "0",
            "http://openweathermap.org/img/w/" + "04d" + ".png",
        ),
    )
}

private fun getWeatherForHour(): List<WeatherForHour> {
    return listOf(
        WeatherForHour(
            0,
            0.0,
            "00.00.00",
            0.0,
            0,
            0.0,
            0,
            Snow(0.0),
            "0",
            0.0,
            0,
            getWeatherList(),
            0,
            0.0,
            0.0,
            "http://openweathermap.org/img/w/" + "04d" + ".png"
        ),
        WeatherForHour(
            0,
            0.0,
            "00.00.00",
            0.0,
            0,
            0.0,
            0,
            Snow(0.0),
            "0",
            0.0,
            0,
            getWeatherList(),
            0,
            0.0,
            0.0,
            "http://openweathermap.org/img/w/" + "04d" + ".png"
        ),
        WeatherForHour(
            0,
            0.0,
            "00.00.00",
            0.0,
            0,
            0.0,
            0,
            Snow(0.0),
            "0",
            0.0,
            0,
            getWeatherList(),
            0,
            0.0,
            0.0,
            "http://openweathermap.org/img/w/" + "04d" + ".png"
        ),
        WeatherForHour(
            0,
            0.0,
            "00.00.00",
            0.0,
            0,
            0.0,
            0,
            Snow(0.0),
            "0",
            0.0,
            0,
            getWeatherList(),
            0,
            0.0,
            0.0,
            "http://openweathermap.org/img/w/" + "04d" + ".png"
        ),
    )
}