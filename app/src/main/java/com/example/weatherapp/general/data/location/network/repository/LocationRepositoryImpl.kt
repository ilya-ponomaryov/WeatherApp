package com.example.weatherapp.general.data.location.network.repository

import com.example.weatherapp.common.Result
import com.example.weatherapp.general.data.location.models.Location
import com.example.weatherapp.general.data.location.models.LocationRequest
import com.example.weatherapp.general.data.location.network.sources.LocationRemoteDataSource
import com.example.weatherapp.general.domain.LocationRepository

class LocationRepositoryImpl(private val locationRemoteDataSource: LocationRemoteDataSource) :
    LocationRepository {

    override suspend fun getLocation(locationRequest: LocationRequest): Result<Location> {
        return locationRemoteDataSource.getLocation(locationRequest)
    }
}