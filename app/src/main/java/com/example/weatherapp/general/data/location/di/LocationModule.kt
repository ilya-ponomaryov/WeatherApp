package com.example.weatherapp.general.data.location.di

import com.example.weatherapp.general.data.location.network.LocationService
import com.example.weatherapp.general.data.location.network.repository.LocationRepositoryImpl
import com.example.weatherapp.general.domain.LocationRepository
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
    fun provideLocationService(retrofit: Retrofit): LocationService = retrofit.create(
        LocationService::class.java
    )

    @Provides
    @Singleton
    fun provideLocationRepository(locationService: LocationService): LocationRepository =
        LocationRepositoryImpl(locationService)
}