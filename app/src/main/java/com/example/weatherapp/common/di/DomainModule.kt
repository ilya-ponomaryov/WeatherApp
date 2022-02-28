package com.example.weatherapp.common.di

import com.example.weatherapp.features.general.domain.WeatherRepository
import com.example.weatherapp.features.general.domain.usecases.GetWeatherFromNetworkUseCase
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
}