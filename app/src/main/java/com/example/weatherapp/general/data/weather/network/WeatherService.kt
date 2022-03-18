package com.example.weatherapp.general.data.weather.network

import com.example.weatherapp.general.data.weather.models.WeatherData
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("/data/2.5/onecall?")
    suspend fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("exclude") exclude: String,
        @Query("units") units: String,
        @Query("lang") lang: String,
        @Query("appid") appid: String,
    ): Response<WeatherData>

    companion object {
        fun newService(retrofit: Retrofit): WeatherService =
            retrofit.create(WeatherService::class.java)
    }
}