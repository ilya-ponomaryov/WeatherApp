package com.example.weatherapp.general.domain.usecases.weather

import com.example.weatherapp.general.data.weather.models.WeatherAndLocation
import com.example.weatherapp.general.domain.LocationRepository
import com.example.weatherapp.general.domain.WeatherRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class WeatherGetter @Inject constructor(
    private val weatherService: WeatherRepository,
    private val locationService: LocationRepository
) {
    operator fun invoke(city: String?): Single<WeatherAndLocation> {
        return locationService.getLocation(city)
            .flatMap { location ->
                weatherService.getWeather(
                    location[0].latitude,
                    location[0].longitude
                ).map { weather ->
                    WeatherAndLocation(weather, location)
                }
            }
    }
}