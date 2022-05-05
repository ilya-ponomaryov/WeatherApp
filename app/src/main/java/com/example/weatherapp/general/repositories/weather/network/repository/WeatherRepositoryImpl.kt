package com.example.weatherapp.general.repositories.weather.network.repository

import com.example.weatherapp.common.utils.Constant
import com.example.weatherapp.common.utils.toDailyEquippedList
import com.example.weatherapp.common.utils.toTodayEquipped
import com.example.weatherapp.general.usecases.weather.models.Weather
import com.example.weatherapp.general.repositories.weather.network.WeatherService
import com.example.weatherapp.general.usecases.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val service: WeatherService) :
    WeatherRepository {
    override suspend fun getWeather(latitude: Double, longitude: Double): Weather =
        withContext(Dispatchers.IO) {
            val result = service.getWeather(
                latitude,
                longitude,
                "minutely, alerts",
                "metric",
                "ru",
                Constant.APPID
            )

            if (result.isSuccessful) {
                return@withContext Weather(
                    toTodayEquipped(result.body()!!.current),
                    toDailyEquippedList(result.body()!!.daily, result.body()!!.hourly)
                )
            } else {
                throw Exception("Error")
            }
        }
}