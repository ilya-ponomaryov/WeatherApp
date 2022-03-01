package com.example.weatherapp.location.domain.usecases

import com.example.weatherapp.location.data.models.LocationRequest
import com.example.weatherapp.location.domain.LocationRepository
import javax.inject.Inject

class GetLocationFromNetworkUseCase @Inject constructor(private val locationRepository: LocationRepository) {
    suspend fun invoke(locationRequest: LocationRequest) = locationRepository.getLocation(locationRequest)
}