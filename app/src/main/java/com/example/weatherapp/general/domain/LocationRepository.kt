package com.example.weatherapp.general.domain

import com.example.weatherapp.general.data.location.models.Location

interface LocationRepository {
    suspend fun getLocation(query: String?) : Location
}