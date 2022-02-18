package com.example.weatherapp.di

import com.example.weatherapp.domain.WeatherRepository
import com.example.weatherapp.domain.usecases.GetWeatherFromNetworkUseCase
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    fun provideGetWeatherFromNetworkUseCase(weatherRepository: WeatherRepository)
    = GetWeatherFromNetworkUseCase(weatherRepository)
}