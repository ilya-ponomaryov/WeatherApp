package com.example.weatherapp.general.repositories.weather

import com.example.weatherapp.general.repositories.weather.models.Current
import com.example.weatherapp.general.repositories.weather.models.Daily
import com.example.weatherapp.general.repositories.weather.models.Hourly
import com.example.weatherapp.general.usecases.weather.models.*
import java.util.*

fun Current.toWeatherForToday(): WeatherForToday =
    WeatherForToday(
        date = "Сегодня, " + dateToToday(date),
        feelsLike = feelsLike,
        temperature = "${temperature.toInt()}°",
        weatherIcon = "http://openweathermap.org/img/w/${aboutWeather.first().icon}.png",
        weatherDescription = "${aboutWeather.first().description}, " +
                "ощущается как ${feelsLike.toInt()}",
    )

private fun dateToToday(date: Int): String {
    val converter = DateConverter()
    return "Сегодня, " + converter.getDateAsString(date)
}

fun toDailyEquippedList(
    daily: List<Daily>,
    hourly: List<Hourly>
): List<WeatherForDay> {
    val converter = HourlyDataConverter()
    val hourlyList = converter.getHourlyData(hourly)

    val result = arrayListOf<WeatherForDay>()

    for ((i, day) in daily.withIndex()) {
        val weatherForHour = arrayListOf<WeatherForHour>()

        if (hourlyList.size > i) {
            weatherForHour.addAll(toHourlyEquippedList(hourlyList[i]))
        } else {
            weatherForHour.addAll(emptyList())
        }

        result.add(day.toWeatherForDay(weatherForHour))
    }
    return result
}

private fun Daily.toWeatherForDay(weatherForHour: List<WeatherForHour>) =
    WeatherForDay(
        date = dateToDaily(date),
        weatherForHour = weatherForHour,
        daytimeTemperature = "${temperature.day.toInt()}°",
        nighttimeTemperature = "${temperature.night.toInt()}°",
        weatherIcon = "http://openweathermap.org/img/w/${aboutWeather.first().icon}.png",
    )

private fun dateToDaily(date: Int): String {
    val dailyDate = DateConverter()
    return dailyDate.getDateAsString(date)
}

fun toHourlyEquippedList(hourly: List<Hourly>): List<WeatherForHour> =
    hourly.map { it.toWeatherForHour() }

private fun Hourly.toWeatherForHour() = WeatherForHour(
    hour = date.toHour(),
    temperature = "${temperature.toInt()}°",
    weatherIcon = "http://openweathermap.org/img/w/${aboutWeather.first().icon}.png"
)

private fun Int.toHour(): String {
    val date = Date(this.toLong() * 1000)
    return "${date.hours}:${date.minutes}0"
}