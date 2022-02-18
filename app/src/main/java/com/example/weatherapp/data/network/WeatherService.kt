package com.example.weatherapp.data.network

import com.example.weatherapp.data.models.Weather
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherService {
    @GET("/data/2.5/onecall?lat={lat}&lon={lon}&exclude={exclude}&units={units}&appid={appid}")
    fun getWeather(
        @Path("lat")lat: Double,
        @Path("lon")lon: Double,
        @Path("exclude")exclude: String,
        @Path("units")units: String,
        @Path("appid")appid: String,
    ): Response<Weather>

    /*companion object {
        fun create() : WeatherService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .baseUrl("https://api.openweathermap.org")
                .build()
            return retrofit.create(WeatherService::class.java)
        }
    }*/
}