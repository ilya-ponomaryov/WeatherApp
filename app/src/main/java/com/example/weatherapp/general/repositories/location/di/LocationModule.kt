package com.example.weatherapp.general.repositories.location.di

import com.example.weatherapp.general.repositories.location.network.LocationService
import com.example.weatherapp.general.repositories.location.network.repository.LocationRepositoryImpl
import com.example.weatherapp.general.usecases.LocationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocationModule {
    @Provides
    @Singleton
    fun provideLocationRepository(service: LocationService): LocationRepository =
        LocationRepositoryImpl(service)

    @Provides
    @Singleton
    fun provideLocationService(retrofit: Retrofit): LocationService =
        retrofit.create(LocationService::class.java)
}