package com.example.weatherapp.location.data.network.sources

import com.example.weatherapp.common.Result
import com.example.weatherapp.location.data.models.Location
import com.example.weatherapp.location.data.models.LocationRequest

interface LocationRemoteDataSource {
    suspend fun getLocation(locationRequest: LocationRequest) : Result<Location>
}