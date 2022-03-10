package com.example.weatherapp.general.domain.usecases.weather

import android.util.Log
import com.example.weatherapp.common.BaseUseCase
import com.example.weatherapp.general.data.weather.models.WeatherData
import com.example.weatherapp.general.data.weather.models.WeatherRequest
import com.example.weatherapp.general.domain.WeatherRepository
import javax.inject.Inject

class GetWeatherFromNetworkUseCase @Inject constructor(private val weatherRepository: WeatherRepository)
    : BaseUseCase<WeatherRequest, WeatherData> {
    override suspend fun invoke(
        params: WeatherRequest,
        callback: BaseUseCase.Callback<WeatherData>
    ) {
        try {
            val result = weatherRepository.getWeather(params)
            if (result.isSuccessful) {
                callback.onSuccess(result.body()!!)
            } else {
                handleThrow()
            }
        } catch (e: Exception) {
            println(e.message)
        }
    }
    private suspend fun handleThrow() {
        throw Exception("Oh, it`s don`t work!")
    }
}