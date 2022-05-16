package com.example.weatherapp.general.repositories.location

import com.example.weatherapp.general.usecases.location.models.Location
import com.example.weatherapp.general.usecases.weather.LocationRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val service: LocationService
) : LocationRepository {
    override fun getLocation(cityName: String): Single<Location> =
        service.getCity(cityName).subscribeOn(Schedulers.io())
}