package com.example.weatherapp.common.utils

import com.example.weatherapp.general.data.weather.models.*
import com.example.weatherapp.general.domain.DateConverter
import com.example.weatherapp.general.domain.HourlyDataConverter
import java.util.*

fun toTodayEquipped(current: Current): WeatherForToday {
    val converter = DateConverter()
    return WeatherForToday(
        clouds = current.clouds,
        dewPoint = current.dew_point,
        date = "Сегодня, " + converter.getDateAsString(current.dt),
        feelsLike = current.feels_like,
        humidity = current.humidity,
        pressure = current.pressure,
        sunrise = current.sunrise,
        sunset = current.sunset,
        temperature = current.temp.toInt().toString() + "°",
        uvi = current.uvi,
        visibility = current.visibility,
        aboutWeather = current.aboutWeather,
        windDegrees = current.wind_deg,
        windGust = current.wind_gust,
        windSpeed = current.wind_speed,
        weatherIcon = "http://openweathermap.org/img/w/" + current.aboutWeather[0].icon + ".png",
        weatherDescription = current.aboutWeather[0].description +
                ", ощущается как " + current.feels_like.toInt().toString(),
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
                dewPoint = day.dew_point,
                date = dailyDate.getDateAsString(day.dt),
                feelsLike = day.feels_like,
                humidity = day.humidity,
                moonPhase = day.moon_phase,
                moonrise = day.moonrise,
                moonSet = day.moonset,
                probabilityOfPrecipitation = day.pop,
                pressure = day.pressure,
                rain = day.rain,
                snow = day.snow,
                sunrise = day.sunrise,
                sunset = day.sunset,
                uvi = day.uvi,
                aboutWeather = day.aboutWeather,
                windDegrees = day.wind_deg,
                windGust = day.wind_gust,
                windSpeed = day.wind_speed,
                weatherForHour = weatherForHour,
                daytimeTemperature = day.temp.day.toInt().toString() + "°",
                eveningTemperature = day.temp.eve.toInt().toString() + "°",
                maxTemperature = day.temp.max.toInt().toString(),
                minTemperature = day.temp.min.toInt().toString(),
                morningTemperature = day.temp.morn.toInt().toString(),
                nighttimeTemperature = day.temp.night.toInt().toString() + "°",
                weatherIcon = "http://openweathermap.org/img/w/" + day.aboutWeather[0].icon + ".png",
            )
        )
    }
    return result
}

fun toHourlyEquippedList(hourly: List<Hourly>): List<WeatherForHour> {
    val result = arrayListOf<WeatherForHour>()
    hourly.forEach {
        val date = Date(it.dt.toLong() * 1000)
        var snow = Snow(0.0)
        if (it.snow != null) {
            snow = it.snow
        }

        result.add(
            WeatherForHour(
                clouds = it.clouds,
                dewPoint = it.dew_point,
                hour = date.hours.toString() + ":" + date.minutes + "0",
                feelsLike = it.feels_like,
                humidity = it.humidity,
                probabilityOfPrecipitation = it.pop,
                pressure = it.pressure,
                snow = snow,
                temperature = it.temp.toInt().toString() + "°",
                uvi = it.uvi,
                visibility = it.visibility,
                aboutWeather = it.aboutWeather,
                windDegrees = it.wind_deg,
                windGust = it.wind_gust,
                windSpeed = it.wind_speed,
                weatherIcon = "http://openweathermap.org/img/w/" + it.aboutWeather[0].icon + ".png"
            )
        )
    }
    return result
}