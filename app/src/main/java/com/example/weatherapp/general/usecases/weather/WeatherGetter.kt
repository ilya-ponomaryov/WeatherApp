package com.example.weatherapp.general.usecases.weather

import com.example.weatherapp.general.usecases.weather.models.City
import com.example.weatherapp.general.usecases.weather.models.Weather
import com.example.weatherapp.general.usecases.weather.models.WeatherAndCity
import io.reactivex.Single
import javax.inject.Inject

interface WeatherGetter {
    operator fun invoke(city: String): Single<WeatherAndCity>
}

class WeatherGetterImpl @Inject constructor(
    private val weatherService: WeatherRepository,
    private val locationService: LocationRepository
): WeatherGetter {
    override operator fun invoke(city: String) = locationService.getLocation(city)
        .flatMap(::getWeatherAndCity)

    private fun getWeatherAndCity(city: City) = weatherService
        .getWeather(city.latitude, city.longitude)
        .map { weather -> WeatherAndCity( city, weather) }
}

interface WeatherRepository {
    fun getWeather(latitude: Double, longitude: Double): Single<Weather>
}

interface LocationRepository {
    fun getLocation(cityName: String): Single<City>
}