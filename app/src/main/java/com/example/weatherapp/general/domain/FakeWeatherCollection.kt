package com.example.weatherapp.general.domain

import com.example.weatherapp.general.data.weather.models.*

fun getFakeWeatherCollection(): WeatherCollection {
    return WeatherCollection(
        getFakeWeatherForToday(),
        getFakeWeatherForDay()
    )
}

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

private fun getWeatherList(): List<Weather> {
    return listOf<Weather>(
        Weather(
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

    val weatherXList = listOf<WeatherX>(
        WeatherX(
            description = "Описанин",
            icon = "04d",
            id = 0,
            main = "main"
        )
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
            weatherXList,
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
            weatherXList,
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
            weatherXList,
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
            weatherXList,
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
            weatherXList,
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
    val weatherXXList = listOf<WeatherXX>(
        WeatherXX(
            description = "Описание",
            icon = "04d",
            id = 0,
            main = "main"
        )
    )

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
            weatherXXList,
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
            weatherXXList,
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
            weatherXXList,
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
            weatherXXList,
            0,
            0.0,
            0.0,
            "http://openweathermap.org/img/w/" + "04d" + ".png"
        ),
    )
}