package com.example.weatherapp.common.di

import com.example.weatherapp.general.domain.WeatherRepository
import com.example.weatherapp.general.domain.usecases.weather.GetWeatherFromNetworkUseCase
import com.example.weatherapp.general.domain.LocationRepository
import com.example.weatherapp.general.domain.usecases.location.GetLocationFromNetworkUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {
    @Provides
    fun provideGetWeatherFromNetworkUseCase(weatherRepository: WeatherRepository, locationRepository: LocationRepository)
    = GetWeatherFromNetworkUseCase(weatherRepository, locationRepository)

    fun provideGetLocationFromNetworkUseCase(locationRepository: LocationRepository)
    = GetLocationFromNetworkUseCase(locationRepository)
}