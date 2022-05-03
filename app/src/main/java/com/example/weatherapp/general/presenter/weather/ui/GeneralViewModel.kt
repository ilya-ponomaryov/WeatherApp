package com.example.weatherapp.general.presenter.weather.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.weatherapp.common.utils.ExceptionCatcher
import com.example.weatherapp.common.utils.MutableSingleEventFlow
import com.example.weatherapp.general.data.weather.models.WeatherForDay
import com.example.weatherapp.general.data.weather.models.WeatherForToday
import com.example.weatherapp.general.domain.getFakeWeatherForDay
import com.example.weatherapp.general.domain.getFakeWeatherForToday
import com.example.weatherapp.general.domain.usecases.weather.WeatherGetter
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.*
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

    fun loadWeather(city: String?) {
        getWeather(city)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { weatherAndLocation ->
                    _weatherForToday.value = weatherAndLocation.weather.weatherForToday
                    _weatherForDay.value = weatherAndLocation.weather.weatherForDays
                    _city.value = weatherAndLocation.location[0].localNames.russian
                },
                { throwable ->
                    Log.d("ViewModel", throwable.message.toString())
                    _error.tryEmit(ExceptionCatcher.getErrorMessage(Exception(throwable)))
                }
            )
    }
}
