package com.example.weatherapp.features.general.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.common.Result
import com.example.weatherapp.data.models.general.WeatherData
import com.example.weatherapp.data.models.general.WeatherRequest
import com.example.weatherapp.domain.general.usecases.GetWeatherFromNetworkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GeneralViewModel @Inject constructor(
    private val getWeatherFromNetworkUseCase: GetWeatherFromNetworkUseCase
) : ViewModel() {
    init {
        getWeatherFromNetwork(
            WeatherRequest(
            52.731118499999994,
            41.434199146086904,
            "minutely,alerts",
            "metric",
            "ru",
            "b4ffacf20b03eabce37d537a7d83a675"
        )
        )
    }

    private val _weatherLiveData = MutableLiveData<WeatherData>()
    val weatherLiveData : LiveData<WeatherData>
    get() = _weatherLiveData

    private fun getWeatherFromNetwork(weatherRequest: WeatherRequest) {
        viewModelScope.launch {
            when(val weatherResult = getWeatherFromNetworkUseCase.invoke(weatherRequest)) {
                is Result.Loading -> {
                    Log.d("ViewModelLog", "Loading")
                }
                is Result.Success -> {
                    Log.d("ViewModelLog", "Success")
                    _weatherLiveData.postValue(weatherResult.data!!)
                }
                is Result.Error -> {
                    Log.d("ViewModelLog", "Error: ${weatherResult.exception}")
                }
            }
        }
    }

}