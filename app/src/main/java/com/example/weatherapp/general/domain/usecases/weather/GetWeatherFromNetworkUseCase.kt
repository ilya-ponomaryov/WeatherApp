package com.example.weatherapp.general.domain.usecases.weather

import com.example.weatherapp.general.data.weather.models.WeatherRequest
import com.example.weatherapp.general.domain.WeatherRepository
import javax.inject.Inject

class GetWeatherFromNetworkUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {
     suspend fun invoke(params: WeatherRequest) = weatherRepository.getWeather(params)

}