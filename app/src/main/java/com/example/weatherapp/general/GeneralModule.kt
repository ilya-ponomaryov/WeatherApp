package com.example.weatherapp.general

import com.example.weatherapp.general.repositories.location.network.LocationService
import com.example.weatherapp.general.repositories.location.network.repository.LocationRepositoryImpl
import com.example.weatherapp.general.repositories.weather.network.WeatherService
import com.example.weatherapp.general.repositories.weather.network.repository.WeatherRepositoryImpl
import com.example.weatherapp.general.usecases.LocationRepository
import com.example.weatherapp.general.usecases.UseCase
import com.example.weatherapp.general.usecases.WeatherRepository
import com.example.weatherapp.general.usecases.weather.WeatherGetter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class GeneralModule {
    @Provides
    @Singleton
    fun provideWeatherRepository(service: WeatherService): WeatherRepository =
        WeatherRepositoryImpl(service)

    @Provides
    @Singleton
    fun provideWeatherService(retrofit: Retrofit): WeatherService =
        retrofit.create(WeatherService::class.java)

    @Provides
    @Singleton
    fun provideLocationRepository(service: LocationService): LocationRepository =
        LocationRepositoryImpl(service)

    @Provides
    @Singleton
    fun provideLocationService(retrofit: Retrofit): LocationService =
        retrofit.create(LocationService::class.java)

    @Provides
    fun provideGetWeatherFromNetwork(
        weatherRepository: WeatherRepository,
        locationRepository: LocationRepository,
    ): UseCase = WeatherGetter(weatherRepository, locationRepository)
}