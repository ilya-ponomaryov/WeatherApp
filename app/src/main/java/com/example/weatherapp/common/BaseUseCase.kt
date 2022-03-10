package com.example.weatherapp.common

interface BaseUseCase<in P, out R> {
    interface Callback<in R> {
        fun onSuccess(result: R)
        fun onError(throwable: Throwable)
    }

    suspend fun invoke(params: P, callback: Callback<R>)
}