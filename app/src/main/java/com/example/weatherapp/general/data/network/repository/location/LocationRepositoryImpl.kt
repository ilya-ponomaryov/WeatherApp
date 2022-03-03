package com.example.weatherapp.general.data.network.repository.location

import com.example.weatherapp.common.Result
import com.example.weatherapp.general.data.models.location.Location
import com.example.weatherapp.general.data.models.location.LocationRequest
import com.example.weatherapp.general.data.network.sources.location.LocationRemoteDataSource
import com.example.weatherapp.general.domain.LocationRepository

class LocationRepositoryImpl(private val locationRemoteDataSource: LocationRemoteDataSource) :
    LocationRepository {

    override suspend fun getLocation(locationRequest: LocationRequest): Result<Location> {
        return locationRemoteDataSource.getLocation(locationRequest)
    }
}