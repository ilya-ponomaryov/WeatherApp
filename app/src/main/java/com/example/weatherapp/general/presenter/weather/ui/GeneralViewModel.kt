package com.example.weatherapp.general.presenter.weather.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.common.utils.ExceptionCatcher
import com.example.weatherapp.common.utils.MutableSingleEventFlow
import com.example.weatherapp.general.usecases.weather.models.WeatherForDay
import com.example.weatherapp.general.usecases.weather.models.WeatherForToday
import com.example.weatherapp.general.usecases.getFakeWeatherForDay
import com.example.weatherapp.general.usecases.getFakeWeatherForToday
import com.example.weatherapp.general.usecases.weather.WeatherGetter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GeneralViewModel @Inject constructor(
    private val getWeather: WeatherGetter
) : ViewModel() {
    private val _weatherForToday = MutableStateFlow<WeatherForToday>(getFakeWeatherForToday())
    val weatherForToday: StateFlow<WeatherForToday> = _weatherForToday.asStateFlow()

    private val _weatherForDay = MutableStateFlow<List<WeatherForDay>>(getFakeWeatherForDay())
    val weatherForDay: StateFlow<List<WeatherForDay>> = _weatherForDay.asStateFlow()

    private val _city = MutableStateFlow<String>("Ваш город")
    val city: StateFlow<String> = _city.asStateFlow()

    private val _error = MutableSingleEventFlow<String>()
    val error: SharedFlow<String> = _error.asSharedFlow()

    fun loadWeather(city: String?) = viewModelScope.launch {
        try {
            val result = getWeather(city)
            _weatherForToday.value = result.weather.weatherForToday
            _weatherForDay.value = result.weather.weatherForDays
            _city.value = result.location[0].localNames.russian
        } catch (e: Exception) {
            _error.emit(ExceptionCatcher.getErrorMessage(e))
        }
    }
}
