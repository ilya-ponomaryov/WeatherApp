package com.example.weatherapp.common.di

import com.example.weatherapp.general.domain.WeatherRepository
import com.example.weatherapp.general.domain.LocationRepository
import com.example.weatherapp.general.domain.usecases.weather.WeatherGetter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {
    @Provides
    fun provideGetWeatherFromNetwork(
        weatherRepository: WeatherRepository,
        locationRepository: LocationRepository,
    ) = WeatherGetter(weatherRepository, locationRepository)
}