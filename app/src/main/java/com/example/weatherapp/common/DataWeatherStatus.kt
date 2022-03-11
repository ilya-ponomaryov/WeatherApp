package com.example.weatherapp.common

sealed class DataWeatherStatus<out R> {
    data class Success<out R>(val data: R): DataWeatherStatus<R>()

    data class Failure(val message: String): DataWeatherStatus<Nothing>()
}
