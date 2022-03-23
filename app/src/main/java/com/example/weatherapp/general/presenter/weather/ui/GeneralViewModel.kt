package com.example.weatherapp.general.presenter.weather.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.common.utils.ExceptionCatcher
import com.example.weatherapp.general.data.weather.models.WeatherData
import com.example.weatherapp.general.domain.getFakeWeatherData
import com.example.weatherapp.general.domain.usecases.weather.WeatherGetter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GeneralViewModel @Inject constructor(
    private val getWeather: WeatherGetter
) : ViewModel() {
    private val _weather = MutableStateFlow<WeatherData>(getFakeWeatherData())
    val weather: StateFlow<WeatherData>
        get() = _weather

    private val _city = MutableStateFlow<String>("")
    val city: StateFlow<String>
        get() = _city

    private val _error = MutableStateFlow<String>("")
    val error: StateFlow<String>
        get() = _error

    fun loadWeather(city: String?) = viewModelScope.launch {
        try {
            val result = getWeather(city)
            _weather.value = result.weatherData
            _city.value = result.location[0].local_names.ru
        } catch (e: Exception) {
            _error.value = ExceptionCatcher.getErrorMessage(e)
        }
    }
}
