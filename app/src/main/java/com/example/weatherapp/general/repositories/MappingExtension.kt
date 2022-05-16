package com.example.weatherapp.general.repositories

import com.example.weatherapp.general.usecases.weather.models.*
import com.example.weatherapp.general.usecases.DateConverter
import com.example.weatherapp.general.usecases.HourlyDataConverter
import java.util.*

fun toTodayEquipped(current: Current): WeatherForToday {
    val converter = DateConverter()
    return WeatherForToday(
        clouds = current.clouds,
        dewPoint = current.dewPoint,
        date = "Сегодня, " + converter.getDateAsString(current.date),
        feelsLike = current.feelsLike,
        humidity = current.humidity,
        pressure = current.pressure,
        sunrise = current.sunrise,
        sunset = current.sunset,
        temperature = current.temperature.toInt().toString() + "°",
        uvi = current.uvi,
        visibility = current.visibility,
        aboutWeather = current.aboutWeather,
        windDegrees = current.windDegrees,
        windGust = current.windGust,
        windSpeed = current.windSpeed,
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
                clouds = day.clouds,
                dewPoint = day.dewPoint,
                date = dailyDate.getDateAsString(day.date),
                feelsLike = day.feelsLike,
                humidity = day.humidity,
                moonPhase = day.moonPhase,
                moonrise = day.moonrise,
                moonSet = day.moonSet,
                probabilityOfPrecipitation = day.probabilityOfPrecipitation,
                pressure = day.pressure,
                rain = day.rain,
                snow = day.snow,
                sunrise = day.sunrise,
                sunset = day.sunset,
                uvi = day.uvi,
                aboutWeather = day.aboutWeather,
                windDegrees = day.windDegrees,
                windGust = day.windGust,
                windSpeed = day.windSpeed,
                weatherForHour = weatherForHour,
                daytimeTemperature = day.temperature.day.toInt().toString() + "°",
                eveningTemperature = day.temperature.evening.toInt().toString() + "°",
                maxTemperature = day.temperature.max.toInt().toString(),
                minTemperature = day.temperature.min.toInt().toString(),
                morningTemperature = day.temperature.morning.toInt().toString(),
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
        var snow = Snow(0.0)
        if (it.snow != null) {
            snow = it.snow
        }

        result.add(
            WeatherForHour(
                clouds = it.clouds,
                dewPoint = it.dewPoint,
                hour = date.hours.toString() + ":" + date.minutes + "0",
                feelsLike = it.feelsLike,
                humidity = it.humidity,
                probabilityOfPrecipitation = it.probabilityOfPrecipitation,
                pressure = it.pressure,
                snow = snow,
                temperature = it.temperature.toInt().toString() + "°",
                uvi = it.uvi,
                visibility = it.visibility,
                aboutWeather = it.aboutWeather,
                windDegrees = it.windDegrees,
                windGust = it.windGust,
                windSpeed = it.windSpeed,
                weatherIcon = "http://openweathermap.org/img/w/" + it.aboutWeather[0].icon + ".png"
            )
        )
    }
    return result
}