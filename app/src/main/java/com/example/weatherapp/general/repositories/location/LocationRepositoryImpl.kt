package com.example.weatherapp.general.repositories.location

import com.example.weatherapp.general.repositories.location.models.LocationItem
import com.example.weatherapp.general.usecases.weather.models.City
import com.example.weatherapp.general.usecases.weather.LocationRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val service: LocationService
) : LocationRepository {
    override fun getLocation(cityName: String): Single<City> =
        service.getCity(cityName)
            .map(::convertToCity)
            .subscribeOn(Schedulers.io())

    private fun convertToCity(location: List<LocationItem>) = City(
        location[0].localNames.russian,
        location[0].latitude,
        location[0].longitude
    )

}