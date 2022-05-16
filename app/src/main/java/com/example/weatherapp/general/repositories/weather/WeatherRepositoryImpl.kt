package com.example.weatherapp.general.repositories.weather

import com.example.weatherapp.general.repositories.toDailyEquippedList
import com.example.weatherapp.general.repositories.toTodayEquipped
import com.example.weatherapp.general.usecases.WeatherRepository
import com.example.weatherapp.general.usecases.weather.models.Weather
import com.example.weatherapp.general.usecases.weather.models.WeatherData
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val service: WeatherService
) : WeatherRepository {
    override fun getWeather(latitude: Double, longitude: Double) = service
        .getWeather(latitude, longitude)
        .map(::convertToWeather)
        .subscribeOn(Schedulers.io())

    private fun convertToWeather(weatherData: WeatherData) = Weather(
        toTodayEquipped(weatherData.current),
        toDailyEquippedList(weatherData.daily, weatherData.hourly)
    )

}