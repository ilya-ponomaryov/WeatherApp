package com.example.weatherapp.general.di

import com.example.weatherapp.general.usecases.WeatherRepository
import com.example.weatherapp.general.usecases.LocationRepository
import com.example.weatherapp.general.usecases.weather.WeatherGetter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class WeatherDomainModule {
    @Provides
    fun provideGetWeatherFromNetwork(
        weatherRepository: WeatherRepository,
        locationRepository: LocationRepository,
    ) = WeatherGetter(weatherRepository, locationRepository)
}