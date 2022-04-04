package com.example.weatherapp.general.domain

import com.example.weatherapp.general.data.weather.models.Daily
import com.example.weatherapp.general.data.weather.models.DailyEquipped
import com.example.weatherapp.general.data.weather.models.Hourly

class DailyMapper(private val daily: List<Daily>, private val hourly: List<Hourly>) {
    fun toDailyEquippedList(): List<DailyEquipped> {
        val converter = HourlyDataConverter()
        val dailyDate = DateConverter()
        val hourlyList = converter.getHourlyData(hourly)
        val result = arrayListOf<DailyEquipped>()

        for ((i, day) in daily.withIndex()) {
            val hourlyLocal = arrayListOf<Hourly>()
            if (hourlyList.size > i) {
                hourlyLocal.addAll(hourlyList[i])
            } else {
                hourlyLocal.addAll(emptyList())
            }
            result.add(DailyEquipped(
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
                day.weather,
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
            ))
        }
        return result
    }

}