package com.example.weatherapp.general.domain.usecases.location

import com.example.weatherapp.common.BaseUseCase
import com.example.weatherapp.general.data.location.models.Location
import com.example.weatherapp.general.data.location.models.LocationRequest
import com.example.weatherapp.general.data.weather.models.WeatherData
import com.example.weatherapp.general.data.weather.models.WeatherRequest
import com.example.weatherapp.general.domain.LocationRepository
import javax.inject.Inject

class GetLocationFromNetworkUseCase @Inject constructor(private val locationRepository: LocationRepository)
    : BaseUseCase<LocationRequest, Location>{
    override suspend fun invoke(params: LocationRequest,
                                callback: BaseUseCase.Callback<Location>) {
        try {
            val result = locationRepository.getLocation(params)
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

