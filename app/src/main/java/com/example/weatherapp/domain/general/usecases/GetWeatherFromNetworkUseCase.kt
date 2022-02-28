package com.example.weatherapp.domain.general.usecases

import com.example.weatherapp.data.models.general.WeatherRequest
import com.example.weatherapp.domain.general.WeatherRepository
import javax.inject.Inject

class GetWeatherFromNetworkUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {
    suspend fun invoke(weatherRequest: WeatherRequest) = weatherRepository.getWeather(weatherRequest)
}