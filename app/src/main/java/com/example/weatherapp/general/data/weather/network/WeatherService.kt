package com.example.weatherapp.general.data.weather.network

import com.example.weatherapp.general.data.weather.models.WeatherData
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("/data/2.5/onecall?")
    fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("exclude") exclude: String,
        @Query("units") units: String,
        @Query("lang") lang: String,
        @Query("appid") appid: String,
    ): Single<WeatherData>
}