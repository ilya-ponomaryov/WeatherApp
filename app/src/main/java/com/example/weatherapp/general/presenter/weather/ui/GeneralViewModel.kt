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
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GeneralViewModel @Inject constructor(
    private val getWeather: WeatherGetter
) : ViewModel() {
    private val _weather = MutableStateFlow<WeatherData>(getFakeWeatherData())
    val weather: StateFlow<WeatherData> = _weather.asStateFlow()

    private val _city = MutableStateFlow<String>("Ваш город")
    val city: StateFlow<String> = _city.asStateFlow()

    private val _error = MutableSharedFlow<String>(0, 1, BufferOverflow.SUSPEND)
    val error: SharedFlow<String> = _error.asSharedFlow()


    fun loadWeather(city: String?) = viewModelScope.launch {
        try {
            val result = getWeather(city)
            _weather.value = result.weatherData
            _city.value = result.location[0].local_names.ru
        } catch (e: Exception) {
            _error.emit(ExceptionCatcher.getErrorMessage(e))
        }
    }
}
