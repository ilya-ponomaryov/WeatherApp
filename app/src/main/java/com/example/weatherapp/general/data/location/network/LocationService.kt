package com.example.weatherapp.general.data.location.network

import com.example.weatherapp.common.utils.Constant
import com.example.weatherapp.general.data.location.models.Location
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationService {
    @GET("/geo/1.0/direct?/limit=1&appid=${Constant.APPID}")
    fun getCity(
        @Query("q") q: String,
    ): Single<Location>
}