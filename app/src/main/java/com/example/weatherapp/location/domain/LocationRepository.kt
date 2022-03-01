package com.example.weatherapp.location.domain

import com.example.weatherapp.common.Result
import com.example.weatherapp.location.data.models.Location
import com.example.weatherapp.location.data.models.LocationRequest

interface LocationRepository {
    suspend fun getWeather(locationRequest: LocationRequest) : Result<Location>
}