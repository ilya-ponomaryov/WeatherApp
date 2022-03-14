package com.example.weatherapp.general.domain.usecases.weather

import com.example.weatherapp.general.data.weather.models.WeatherAndLocation
import com.example.weatherapp.general.domain.LocationRepository
import com.example.weatherapp.general.domain.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetWeatherFromNetworkUseCase @Inject constructor(
     private val weatherRepository: WeatherRepository,
     private val locationRepository: LocationRepository
     ) {
     suspend operator fun invoke(city: String?): WeatherAndLocation
     = withContext(Dispatchers.Default) {
               val locationResult = locationRepository.getLocation(city)
               val weatherResult = weatherRepository.getWeather(
                    locationResult[0].lat,
                    locationResult[0].lon)
               WeatherAndLocation(weatherResult, locationResult)
          }
}