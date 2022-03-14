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
    private val _weatherAndLocationDataLiveData = MutableLiveData<WeatherAndLocation>()
    val weatherAndLocationDataLiveData: LiveData<WeatherAndLocation>
        get() = _weatherAndLocationDataLiveData

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String>
        get() = _errorLiveData

    fun getWeatherFromNetwork(city: String?) {
        viewModelScope.launch {
            try {
                val result = getWeatherFromNetworkUseCase.invoke(city)
                _weatherAndLocationDataLiveData.value = result
            } catch (e: Exception) {
                _errorLiveData.value = ExceptionCatcher.getErrorMessage(e)
            }
        }
    }
}