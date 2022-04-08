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
                    it.clouds,
                    it.dew_point,
                    date.hours.toString() + ":" + date.minutes + "0",
                    it.feels_like,
                    it.humidity,
                    it.pop,
                    it.pressure,
                    snow,
                    it.temp.toInt().toString() + "°",
                    it.uvi,
                    it.visibility,
                    it.aboutWeather,
                    it.wind_deg,
                    it.wind_gust,
                    it.wind_speed,
                    "http://openweathermap.org/img/w/" + it.aboutWeather[0].icon + ".png"
                )
            )
        }
        return result
    }
}