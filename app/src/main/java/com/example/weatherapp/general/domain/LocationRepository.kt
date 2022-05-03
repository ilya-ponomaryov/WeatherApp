package com.example.weatherapp.general.domain

import com.example.weatherapp.general.data.location.models.Location
import io.reactivex.rxjava3.core.Single

interface LocationRepository {
    fun getLocation(cityName: String?): Single<Location>
}