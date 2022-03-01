package com.example.weatherapp.common.di

import com.example.weatherapp.general.domain.WeatherRepository
import com.example.weatherapp.general.domain.usecases.GetWeatherFromNetworkUseCase
import com.example.weatherapp.location.domain.LocationRepository
import com.example.weatherapp.location.domain.usecases.GetLocationFromNetworkUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {
    @Provides
    fun provideGetWeatherFromNetworkUseCase(weatherRepository: WeatherRepository)
    = GetWeatherFromNetworkUseCase(weatherRepository)

    fun provideGetLocationFromNetworkUseCase(locationRepository: LocationRepository)
    = GetLocationFromNetworkUseCase(locationRepository)
}