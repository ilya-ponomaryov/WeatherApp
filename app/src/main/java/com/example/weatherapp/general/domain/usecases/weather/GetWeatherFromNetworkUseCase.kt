package com.example.weatherapp.general.domain.usecases.weather

import android.util.Log
import com.example.weatherapp.common.BaseUseCase
import com.example.weatherapp.general.data.weather.models.WeatherData
import com.example.weatherapp.general.data.weather.models.WeatherRequest
import com.example.weatherapp.general.domain.WeatherRepository
import javax.inject.Inject

class GetWeatherFromNetworkUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {
     suspend fun invoke(params: WeatherRequest) = weatherRepository.getWeather(params)

}