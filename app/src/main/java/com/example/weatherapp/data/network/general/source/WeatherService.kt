package com.example.weatherapp.data.network.general.source

import com.example.weatherapp.data.models.general.WeatherData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("/data/2.5/onecall?")
    suspend fun getWeather(
        @Query("lat")lat: Double,
        @Query("lon")lon: Double,
        @Query("exclude")exclude: String,
        @Query("units")units: String,
        @Query("lang")lang: String,
        @Query("appid")appid: String,
    ): Response<WeatherData>

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