package com.example.weatherapp.general.data.location.network.repository

import com.example.weatherapp.common.utils.Constant
import com.example.weatherapp.general.data.location.models.Location
import com.example.weatherapp.general.data.location.network.LocationService
import com.example.weatherapp.general.domain.LocationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.Exception

class LocationRepositoryImpl @Inject constructor(private val service: LocationService) :
    LocationRepository {

    override suspend fun getLocation(query: String?): Location
    = withContext(Dispatchers.IO) {
        val city = if (!query.isNullOrEmpty() && query.isNotBlank()) {
            query
        } else {
            "Тамбов"
        }
        val result = service.getCity(
            city,
            1,
            Constant.APPID)

        if (result.isSuccessful) {
            return@withContext result.body()!!
        } else {
            throw Exception("Error")
        }

    }
}