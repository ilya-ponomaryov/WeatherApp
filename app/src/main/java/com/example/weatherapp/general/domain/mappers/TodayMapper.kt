package com.example.weatherapp.general.domain.mappers

import com.example.weatherapp.general.data.weather.models.Current
import com.example.weatherapp.general.data.weather.models.WeatherForToday
import com.example.weatherapp.general.domain.DateConverter

class TodayMapper(private val current: Current) {
    fun toTodayEquipped(): WeatherForToday {
        val converter = DateConverter()
        return WeatherForToday(
            current.clouds,
            current.dew_point,
            "Сегодня, " + converter.getDateAsString(current.dt),
            current.feels_like,
            current.humidity,
            current.pressure,
            current.sunrise,
            current.sunset,
            current.temp.toInt().toString() + "°",
            current.uvi,
            current.visibility,
            current.weather,
            current.wind_deg,
            current.wind_gust,
            current.wind_speed,
            "http://openweathermap.org/img/w/" + current.weather[0].icon + ".png",
            current.weather[0].description +
                    ", ощущается как " + current.feels_like.toInt().toString(),
        )
    }
}