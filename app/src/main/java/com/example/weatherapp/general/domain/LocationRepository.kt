package com.example.weatherapp.general.domain

import com.example.weatherapp.general.data.location.models.Location
import com.example.weatherapp.general.data.location.models.LocationRequest

interface LocationRepository {
    suspend fun getLocation(query: String) : Location
}