package com.example.weatherapp.general.domain.usecases

import com.example.weatherapp.general.data.models.WeatherRequest
import com.example.weatherapp.general.domain.WeatherRepository
import javax.inject.Inject

class GetWeatherFromNetworkUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {
    suspend fun invoke(weatherRequest: WeatherRequest) = weatherRepository.getWeather(weatherRequest)
}