package com.example.weatherapp.general.usecases

import com.example.weatherapp.general.usecases.location.models.Location
import io.reactivex.Single

interface LocationRepository {
    fun getLocation(cityName: String): Single<Location>
}