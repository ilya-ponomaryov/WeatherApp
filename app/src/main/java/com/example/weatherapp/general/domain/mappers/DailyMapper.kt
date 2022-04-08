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
            val weatherForHour = arrayListOf<WeatherForHour>()
            if (hourlyList.size > i) {
                val hourlyMapper = HourlyMapper(hourlyList[i])
                weatherForHour.addAll(hourlyMapper.toHourlyEquippedList())
            } else {
                weatherForHour.addAll(emptyList())
            }
            result.add(WeatherForDay(
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
            ))
        }
        return result
    }

}