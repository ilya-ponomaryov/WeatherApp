package com.example.weatherapp.general.data.location.network

import com.example.weatherapp.general.data.location.models.Location
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationService {
    @GET("/geo/1.0/direct?")
    fun getCity(
        @Query("q") q: String,
        @Query("limit") limit: Int,
        @Query("appid") appid: String
    ): Single<Location>
}