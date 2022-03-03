package com.example.weatherapp.general.data.network.sources.location

import com.example.weatherapp.common.Result
import com.example.weatherapp.general.data.models.location.Location
import com.example.weatherapp.general.data.models.location.LocationRequest

interface LocationRemoteDataSource {
    suspend fun getLocation(locationRequest: LocationRequest) : Result<Location>
}