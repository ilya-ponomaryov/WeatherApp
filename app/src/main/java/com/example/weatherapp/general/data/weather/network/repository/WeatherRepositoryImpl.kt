package com.example.weatherapp.general.data.weather.network.repository

import com.example.weatherapp.common.utils.Constant
import com.example.weatherapp.general.data.weather.models.Weather
import com.example.weatherapp.general.data.weather.network.WeatherService
import com.example.weatherapp.general.domain.WeatherRepository
import com.example.weatherapp.general.domain.mappers.DailyMapper
import com.example.weatherapp.general.domain.mappers.TodayMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val service: WeatherService) :
    WeatherRepository {
    override suspend fun getWeather(lat: Double, lon: Double): Weather =
        withContext(Dispatchers.IO) {
            val result = service.getWeather(
                lat,
                lon,
                "minutely, alerts",
                "metric",
                "ru",
                Constant.APPID
            )
            if (result.isSuccessful) {
                val todayMapper = TodayMapper(result.body()!!.current)
                val dayMapper = DailyMapper(result.body()!!.daily, result.body()!!.hourly)
                return@withContext Weather(
                    todayMapper.toTodayEquipped(),
                    dayMapper.toDailyEquippedList())
            } else {
                throw Exception("Error")
            }
        }
}