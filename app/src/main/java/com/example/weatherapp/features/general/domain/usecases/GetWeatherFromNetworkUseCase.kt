package com.example.weatherapp.features.general.domain.usecases

import com.example.weatherapp.features.general.data.models.WeatherRequest
import com.example.weatherapp.features.general.domain.WeatherRepository
import javax.inject.Inject

class GetWeatherFromNetworkUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {
    suspend fun invoke(weatherRequest: WeatherRequest) = weatherRepository.getWeather(weatherRequest)
}