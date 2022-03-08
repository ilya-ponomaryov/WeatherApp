package com.example.weatherapp.general.data.location.network.sources

import com.example.weatherapp.common.Result
import com.example.weatherapp.general.data.location.models.Location
import com.example.weatherapp.general.data.location.models.LocationRequest
import com.example.weatherapp.general.data.location.network.LocationService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.Exception

class LocationRemoteDataSourceImpl @Inject constructor(
    private val locationService: LocationService
) : LocationRemoteDataSource {
    override suspend fun getLocation(locationRequest: LocationRequest): Result<Location>
    = withContext(Dispatchers.IO) {
        try {
            val response = locationService.getCity(
                locationRequest.q,
                locationRequest.limit,
                locationRequest.appid
            )
            if (response.isSuccessful) {
                return@withContext Result.Success(response.body()!!)
            } else {
                return@withContext Result.Error(Exception(response.message()))
            }
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }
    }
}