package com.example.weatherapp.general.usecases

import com.example.weatherapp.general.usecases.location.models.Location

interface LocationRepository {
    suspend fun getLocation(cityName: String?): Location
}