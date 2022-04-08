package com.example.weatherapp.general.domain.mappers

import com.example.weatherapp.general.data.weather.models.Hourly
import com.example.weatherapp.general.data.weather.models.WeatherForHour
import com.example.weatherapp.general.data.weather.models.Snow
import java.util.*

class HourlyMapper(private val hourly: List<Hourly>) {
    fun toHourlyEquippedList(): List<WeatherForHour> {
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
}