package com.example.weatherapp.general.usecases.weather

import com.example.weatherapp.general.domain.LocationRepository
import com.example.weatherapp.general.domain.WeatherRepository
import com.example.weatherapp.general.usecases.location.models.Location
import com.example.weatherapp.general.usecases.weather.models.WeatherAndLocation
import javax.inject.Inject

class WeatherGetter @Inject constructor(
    private val weatherService: WeatherRepository,
    private val locationService: LocationRepository
) {
    operator fun invoke(city: String) = locationService.getLocation(city)
        .flatMap(::getWeatherAndLocation)


    private fun getWeatherAndLocation(location: Location) = weatherService
        .getWeather(location[0].latitude, location[0].longitude)
        .map { weather -> WeatherAndLocation(weather, location) }

}