package com.example.weatherapp.general.usecases

import com.example.weatherapp.general.usecases.weather.models.WeatherAndLocation

interface UseCase {
    suspend operator fun invoke(city: String?): WeatherAndLocation

}