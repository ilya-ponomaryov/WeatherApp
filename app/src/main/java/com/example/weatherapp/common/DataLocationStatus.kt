package com.example.weatherapp.common

sealed class DataLocationStatus<out R> {
    data class Success<out R>(val data: R): DataLocationStatus<R>()

    data class Failure(val message: String): DataLocationStatus<Nothing>()
}