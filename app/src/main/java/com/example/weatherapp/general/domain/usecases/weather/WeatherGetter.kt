package com.example.weatherapp.general.domain.usecases.weather

import com.example.weatherapp.general.data.weather.models.WeatherAndLocation
import com.example.weatherapp.general.domain.LocationRepository
import com.example.weatherapp.general.domain.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherGetter @Inject constructor(
    private val weatherService: WeatherRepository,
    private val locationService: LocationRepository
) {
    suspend operator fun invoke(city: String?): WeatherAndLocation =
        withContext(Dispatchers.Default) {
            val locationResult = locationService.getLocation(city)
            val weatherResult = weatherService.getWeather(
                locationResult[0].latitude,
                locationResult[0].longitude
            )
            WeatherAndLocation(weatherResult, locationResult)
        }
}