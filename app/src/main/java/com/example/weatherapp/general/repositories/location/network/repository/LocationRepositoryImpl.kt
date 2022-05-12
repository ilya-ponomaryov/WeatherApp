package com.example.weatherapp.general.repositories.location.network.repository

import com.example.weatherapp.general.repositories.location.network.LocationService
import com.example.weatherapp.general.usecases.LocationRepository
import com.example.weatherapp.general.usecases.location.models.Location
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val service: LocationService
) : LocationRepository {
    override fun getLocation(cityName: String): Single<Location> =
        service.getCity(cityName).subscribeOn(Schedulers.io())
}