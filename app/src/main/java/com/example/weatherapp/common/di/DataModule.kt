package com.example.weatherapp.common.di

import com.example.weatherapp.data.network.general.source.WeatherRemoteDataSource
import com.example.weatherapp.data.network.general.source.WeatherRemoteDataSourceImpl
import com.example.weatherapp.data.network.general.source.WeatherService
import com.example.weatherapp.data.repository.general.WeatherRepositoryImpl
import com.example.weatherapp.domain.general.WeatherRepository
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    fun provideBaseUrl() : String = "https://api.openweathermap.org"

    @Provides
    @Singleton
    fun provideRetrofit(BASE_URL: String) : Retrofit
    = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_URL)
        .build()

    @Provides
    @Singleton
    fun provideWeatherService(retrofit: Retrofit) : WeatherService = retrofit.create(WeatherService::class.java)

    @Provides
    @Singleton
    fun provideWeatherRemoteDataSource(weatherService: WeatherService): WeatherRemoteDataSource
            = WeatherRemoteDataSourceImpl(weatherService)

    @Provides
    @Singleton
    fun provideWeatherRepository(weatherRemoteDataSource: WeatherRemoteDataSource): WeatherRepository
    = WeatherRepositoryImpl(weatherRemoteDataSource = weatherRemoteDataSource)







}