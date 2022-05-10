package com.example.weatherapp.general.data.weather.network.repository

import com.example.weatherapp.common.utils.toDailyEquippedList
import com.example.weatherapp.common.utils.toTodayEquipped
import com.example.weatherapp.general.data.weather.models.Weather
import com.example.weatherapp.general.data.weather.models.WeatherData
import com.example.weatherapp.general.data.weather.network.WeatherService
import com.example.weatherapp.general.domain.WeatherRepository
import io.reactivex.Single
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val service: WeatherService) :
    WeatherRepository {
    override fun getWeather(latitude: Double, longitude: Double): Single<Weather> {
        return service.getWeather(
            latitude,
            longitude,
        ).map { weatherData -> convertToWeather(weatherData)}
    }

    private fun convertToWeather(weatherData: WeatherData): Weather {
        return Weather(
            toTodayEquipped(weatherData.current),
            toDailyEquippedList(weatherData.daily, weatherData.hourly)
        )
    }
}