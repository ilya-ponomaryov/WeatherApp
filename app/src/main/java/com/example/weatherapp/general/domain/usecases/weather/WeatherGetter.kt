package com.example.weatherapp.general.domain.usecases.weather

import com.example.weatherapp.general.data.location.models.Location
import com.example.weatherapp.general.data.weather.models.WeatherAndLocation
import com.example.weatherapp.general.domain.LocationRepository
import com.example.weatherapp.general.domain.WeatherRepository
import io.reactivex.Single
import javax.inject.Inject

class WeatherGetter @Inject constructor(
    private val weatherService: WeatherRepository,
    private val locationService: LocationRepository
) {
    operator fun invoke(city: String): Single<WeatherAndLocation> {
        return locationService.getLocation(city)
            .flatMap { location -> getWeatherByLocation(location)}
    }

    private fun getWeatherByLocation(location: Location): Single<WeatherAndLocation> {
        return weatherService.getWeather(
            location[0].latitude,
            location[0].longitude
        ).map { weather ->
            WeatherAndLocation(weather, location)
        }
    }
}