package com.example.weatherapp.location.data.network.repository

import com.example.weatherapp.common.Result
import com.example.weatherapp.location.data.models.Location
import com.example.weatherapp.location.data.models.LocationRequest
import com.example.weatherapp.location.data.network.sources.LocationRemoteDataSource
import com.example.weatherapp.location.domain.LocationRepository

class LocationRepositoryImpl(private val locationRemoteDataSource: LocationRemoteDataSource) : LocationRepository {

    override suspend fun getWeather(locationRequest: LocationRequest): Result<Location> {
        return locationRemoteDataSource.getLocation(locationRequest)
    }
}