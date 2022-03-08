package com.example.weatherapp.common.di

import com.example.weatherapp.general.data.weather.network.sources.WeatherRemoteDataSource
import com.example.weatherapp.general.data.weather.network.sources.WeatherRemoteDataSourceImpl
import com.example.weatherapp.general.data.weather.network.WeatherService
import com.example.weatherapp.general.data.weather.network.repository.WeatherRepositoryImpl
import com.example.weatherapp.general.domain.WeatherRepository
import com.example.weatherapp.general.data.location.network.LocationService
import com.example.weatherapp.general.data.location.network.repository.LocationRepositoryImpl
import com.example.weatherapp.general.data.location.network.sources.LocationRemoteDataSource
import com.example.weatherapp.general.data.location.network.sources.LocationRemoteDataSourceImpl
import com.example.weatherapp.general.domain.LocationRepository
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

    @Provides
    @Singleton
    fun provideLocationService(retrofit: Retrofit) : LocationService = retrofit.create(
        LocationService::class.java)

    @Provides
    @Singleton
    fun provideLocationRemoteDataSource(locationService: LocationService) : LocationRemoteDataSource
    = LocationRemoteDataSourceImpl(locationService)

    @Provides
    @Singleton
    fun provideLocationRepository(locationRemoteDataSource: LocationRemoteDataSource): LocationRepository
    = LocationRepositoryImpl(locationRemoteDataSource)






}