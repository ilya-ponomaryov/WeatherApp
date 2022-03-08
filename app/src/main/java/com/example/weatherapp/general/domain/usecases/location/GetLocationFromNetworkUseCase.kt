package com.example.weatherapp.general.domain.usecases.location

import com.example.weatherapp.general.data.location.models.LocationRequest
import com.example.weatherapp.general.domain.LocationRepository
import javax.inject.Inject

class GetLocationFromNetworkUseCase @Inject constructor(private val locationRepository: LocationRepository) {
    suspend fun invoke(locationRequest: LocationRequest) = locationRepository.getLocation(locationRequest)
}