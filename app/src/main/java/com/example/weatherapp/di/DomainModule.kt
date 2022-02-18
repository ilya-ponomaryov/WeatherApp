package com.example.weatherapp.di

import com.example.weatherapp.domain.WeatherRepository
import com.example.weatherapp.domain.usecases.GetWeatherFromNetworkUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {
    @Provides
    fun provideGetWeatherFromNetworkUseCase(weatherRepository: WeatherRepository)
    = GetWeatherFromNetworkUseCase(weatherRepository)
}