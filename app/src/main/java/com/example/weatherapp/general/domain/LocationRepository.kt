package com.example.weatherapp.general.domain

import com.example.weatherapp.common.Result
import com.example.weatherapp.general.data.models.location.Location
import com.example.weatherapp.general.data.models.location.LocationRequest

interface LocationRepository {
    suspend fun getLocation(locationRequest: LocationRequest) : Result<Location>
}