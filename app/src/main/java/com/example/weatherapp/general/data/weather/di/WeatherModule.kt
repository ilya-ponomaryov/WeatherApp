package com.example.weatherapp.general.data.weather.di

import com.example.weatherapp.general.data.weather.network.WeatherService
import com.example.weatherapp.general.data.weather.network.repository.WeatherRepositoryImpl
import com.example.weatherapp.general.domain.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class WeatherModule {

    @Provides
    @Singleton
    fun provideWeatherRepository(service: WeatherService): WeatherRepository =
        WeatherRepository.newRepository(service)

    @Provides
    @Singleton
    fun provideWeatherService(retrofit: Retrofit): WeatherService =
        WeatherService.newService(retrofit)
}