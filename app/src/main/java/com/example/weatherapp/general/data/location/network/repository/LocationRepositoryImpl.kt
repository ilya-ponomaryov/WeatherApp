package com.example.weatherapp.general.data.location.network.repository

import com.example.weatherapp.general.data.location.models.Location
import com.example.weatherapp.general.data.location.models.LocationRequest
import com.example.weatherapp.general.data.location.network.LocationService
import com.example.weatherapp.general.domain.LocationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(private val locationService: LocationService) :
    LocationRepository {

    override suspend fun getLocation(locationRequest: LocationRequest): Response<Location>
    = withContext(Dispatchers.IO) {
        return@withContext locationService.getCity(
            locationRequest.q,
            locationRequest.limit,
            locationRequest.appid
        )

    }
}