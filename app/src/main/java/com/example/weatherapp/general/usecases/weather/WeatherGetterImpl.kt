package com.example.weatherapp.general.usecases.weather

import com.example.weatherapp.general.usecases.location.models.Location
import com.example.weatherapp.general.usecases.weather.models.Weather
import com.example.weatherapp.general.usecases.weather.models.WeatherAndLocation
import io.reactivex.Single
import javax.inject.Inject

class WeatherGetterImpl @Inject constructor(
    private val weatherService: WeatherRepository,
    private val locationService: LocationRepository
): WeatherGetter {
    override operator fun invoke(city: String) = locationService.getLocation(city)
        .flatMap(::getWeatherAndLocation)


    private fun getWeatherAndLocation(location: Location) = weatherService
        .getWeather(location[0].latitude, location[0].longitude)
        .map { weather -> WeatherAndLocation(weather, location) }

}

interface WeatherGetter {
    operator fun invoke(city: String): Single<WeatherAndLocation>
}

interface LocationRepository {
    fun getLocation(cityName: String): Single<Location>
}

interface WeatherRepository {
    fun getWeather(latitude: Double, longitude: Double): Single<Weather>
}