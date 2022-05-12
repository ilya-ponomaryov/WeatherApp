package com.example.weatherapp.general.usecases

import com.example.weatherapp.general.usecases.weather.models.WeatherAndLocation
import io.reactivex.Single

interface UseCase {
    operator fun invoke(city: String): Single<WeatherAndLocation>

}