package com.example.weatherapp.general.data.location.network.repository

import com.example.weatherapp.common.utils.Constant
import com.example.weatherapp.general.data.location.models.Location
import com.example.weatherapp.general.data.location.network.LocationService
import com.example.weatherapp.general.domain.LocationRepository
import io.reactivex.Single
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(private val service: LocationService) :
    LocationRepository {
    override fun getLocation(cityName: String?): Single<Location> {
        val city = if (!cityName.isNullOrEmpty() && cityName.isNotBlank()) {
            cityName
        } else {
            "Тамбов"
        }
        return service.getCity(city, 1, Constant.APPID)
    }

}