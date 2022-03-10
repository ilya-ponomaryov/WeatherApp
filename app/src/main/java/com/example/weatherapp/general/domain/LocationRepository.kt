package com.example.weatherapp.general.domain

import com.example.weatherapp.general.data.location.models.Location
import com.example.weatherapp.general.data.location.models.LocationRequest
import retrofit2.Response

interface LocationRepository {
    suspend fun getLocation(locationRequest: LocationRequest) : Response<Location>
}