package com.example.weatherapp.domain.usecases

import com.example.weatherapp.data.models.WeatherRequest
import com.example.weatherapp.domain.WeatherRepository
import javax.inject.Inject

class GetWeatherFromNetworkUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {
    suspend fun invoke(weatherRequest: WeatherRequest) = weatherRepository.getWeather(weatherRequest)
}