package com.example.weatherapp.general.data.location.network.sources

import com.example.weatherapp.common.Result
import com.example.weatherapp.general.data.location.models.Location
import com.example.weatherapp.general.data.location.models.LocationRequest

interface LocationRemoteDataSource {
    suspend fun getLocation(locationRequest: LocationRequest) : Result<Location>
}