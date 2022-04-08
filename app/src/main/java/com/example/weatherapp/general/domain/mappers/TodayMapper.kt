package com.example.weatherapp.general.domain.mappers

import com.example.weatherapp.general.data.weather.models.Current
import com.example.weatherapp.general.data.weather.models.WeatherForToday
import com.example.weatherapp.general.domain.DateConverter

class TodayMapper(private val current: Current) {
    fun toTodayEquipped(): WeatherForToday {
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
}