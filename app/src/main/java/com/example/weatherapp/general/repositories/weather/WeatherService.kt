package com.example.weatherapp.general.repositories.weather

import com.example.weatherapp.common.utils.Constant
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("/data/2.5/onecall?/exclude=minutely,alerts&units=metric&lang=ru&appid=${Constant.APPID}")
    fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
    ): Single<WeatherData>
}