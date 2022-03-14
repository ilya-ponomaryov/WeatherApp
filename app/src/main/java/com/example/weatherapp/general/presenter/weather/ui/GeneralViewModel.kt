package com.example.weatherapp.general.presenter.weather.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.common.utils.ExceptionCatcher
import com.example.weatherapp.general.domain.usecases.weather.GetWeatherFromNetworkUseCase
import com.example.weatherapp.general.data.weather.models.WeatherAndLocation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GeneralViewModel @Inject constructor(
    private val getWeatherFromNetworkUseCase: GetWeatherFromNetworkUseCase
) : ViewModel() {
    private val _weatherAndLocationData = MutableLiveData<WeatherAndLocation>()
    val weatherAndLocationData: LiveData<WeatherAndLocation>
        get() = _weatherAndLocationData

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    fun getWeatherFromNetwork(city: String?)  = viewModelScope.launch {
            try {
                val result = getWeatherFromNetworkUseCase(city)
                _weatherAndLocationData.value = result
            } catch (e: Exception) {
                _error.value = ExceptionCatcher.getErrorMessage(e)
            }
    }
}
