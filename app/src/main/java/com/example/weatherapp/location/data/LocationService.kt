package com.example.weatherapp.location.data

import com.example.weatherapp.location.data.models.Location
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface LocationService {
    @GET("/geo/1.0/direct?")
    fun getCity(
        @Query("q")q: String,
        @Query("limit")limit: Int,
        @Query("appid")appid: String) : Response<Location>
}