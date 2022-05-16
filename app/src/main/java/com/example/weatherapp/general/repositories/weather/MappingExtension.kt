package com.example.weatherapp.general.repositories.weather

import com.example.weatherapp.general.repositories.weather.models.Current
import com.example.weatherapp.general.repositories.weather.models.Daily
import com.example.weatherapp.general.repositories.weather.models.Hourly
import com.example.weatherapp.general.usecases.weather.models.*
import java.util.*

fun toTodayEquipped(current: Current): WeatherForToday {
    val converter = DateConverter()
    return WeatherForToday(
        date = "Сегодня, " + converter.getDateAsString(current.date),
        feelsLike = current.feelsLike,
        temperature = current.temperature.toInt().toString() + "°",
        weatherIcon = "http://openweathermap.org/img/w/" + current.aboutWeather[0].icon + ".png",
        weatherDescription = current.aboutWeather[0].description +
                ", ощущается как " + current.feelsLike.toInt().toString(),
    )
}

fun toDailyEquippedList(
    daily: List<Daily>,
    hourly: List<Hourly>
): List<WeatherForDay> {
    val converter = HourlyDataConverter()
    val dailyDate = DateConverter()
    val hourlyList = converter.getHourlyData(hourly)
    val result = arrayListOf<WeatherForDay>()

    for ((i, day) in daily.withIndex()) {
        val weatherForHour = arrayListOf<WeatherForHour>()
        if (hourlyList.size > i) {
            weatherForHour.addAll(toHourlyEquippedList(hourlyList[i]))
        } else {
            weatherForHour.addAll(emptyList())
        }
        result.add(
            WeatherForDay(
                date = dailyDate.getDateAsString(day.date),
                weatherForHour = weatherForHour,
                daytimeTemperature = day.temperature.day.toInt().toString() + "°",
                nighttimeTemperature = day.temperature.night.toInt().toString() + "°",
                weatherIcon = "http://openweathermap.org/img/w/" + day.aboutWeather[0].icon + ".png",
            )
        )
    }
    return result
}

fun toHourlyEquippedList(hourly: List<Hourly>): List<WeatherForHour> {
    val result = arrayListOf<WeatherForHour>()
    hourly.forEach {
        val date = Date(it.date.toLong() * 1000)

        result.add(
            WeatherForHour(
                hour = date.hours.toString() + ":" + date.minutes + "0",
                temperature = it.temperature.toInt().toString() + "°",
                weatherIcon = "http://openweathermap.org/img/w/" + it.aboutWeather[0].icon + ".png"
            )
        )
    }
    return result
}