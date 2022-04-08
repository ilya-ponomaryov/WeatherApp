package com.example.weatherapp.general.domain.mappers

import com.example.weatherapp.general.data.weather.models.Daily
import com.example.weatherapp.general.data.weather.models.WeatherForDay
import com.example.weatherapp.general.data.weather.models.Hourly
import com.example.weatherapp.general.data.weather.models.WeatherForHour
import com.example.weatherapp.general.domain.DateConverter
import com.example.weatherapp.general.domain.HourlyDataConverter

class DailyMapper(private val daily: List<Daily>, private val hourly: List<Hourly>) {
    fun toDailyEquippedList(): List<WeatherForDay> {
        val converter = HourlyDataConverter()
        val dailyDate = DateConverter()
        val hourlyList = converter.getHourlyData(hourly)
        val result = arrayListOf<WeatherForDay>()

        for ((i, day) in daily.withIndex()) {
            val hourlyLocal = arrayListOf<WeatherForHour>()
            if (hourlyList.size > i) {
                val hourlyMapper = HourlyMapper(hourlyList[i])
                hourlyLocal.addAll(hourlyMapper.toHourlyEquippedList())
            } else {
                hourlyLocal.addAll(emptyList())
            }
            result.add(WeatherForDay(
                day.clouds,
                day.dew_point,
                dailyDate.getDateAsString(day.dt),
                day.feels_like,
                day.humidity,
                day.moon_phase,
                day.moonrise,
                day.moonset,
                day.pop,
                day.pressure,
                day.rain,
                day.snow,
                day.sunrise,
                day.sunset,
                day.uvi,
                day.aboutWeather,
                day.wind_deg,
                day.wind_gust,
                day.wind_speed,
                hourlyLocal,
                day.temp.day.toInt().toString() + "°",
                day.temp.eve.toInt().toString() + "°",
                day.temp.max.toInt().toString(),
                day.temp.min.toInt().toString(),
                day.temp.morn.toInt().toString(),
                day.temp.night.toInt().toString() + "°",
                "http://openweathermap.org/img/w/" + day.aboutWeather[0].icon + ".png",
            ))
        }
        return result
    }

}