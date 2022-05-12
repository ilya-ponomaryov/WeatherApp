package com.example.weatherapp.general

import androidx.lifecycle.ViewModel
import com.example.weatherapp.common.utils.ExceptionCatcher
import com.example.weatherapp.common.utils.MutableSingleEventFlow
import com.example.weatherapp.general.usecases.getStubWeatherForDay
import com.example.weatherapp.general.usecases.getStubWeatherForToday
import com.example.weatherapp.general.usecases.weather.WeatherGetter
import com.example.weatherapp.general.usecases.weather.models.WeatherAndLocation
import com.example.weatherapp.general.usecases.weather.models.WeatherForDay
import com.example.weatherapp.general.usecases.weather.models.WeatherForToday
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class GeneralViewModel @Inject constructor(
    private val getWeather: WeatherGetter
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val _weatherForToday = MutableStateFlow<WeatherForToday>(getStubWeatherForToday())
    val weatherForToday: StateFlow<WeatherForToday> = _weatherForToday.asStateFlow()

    private val _weatherForDay = MutableStateFlow<List<WeatherForDay>>(getStubWeatherForDay())
    val weatherForDay: StateFlow<List<WeatherForDay>> = _weatherForDay.asStateFlow()

    private val _city = MutableStateFlow<String>("Ваш город")
    val city: StateFlow<String> = _city.asStateFlow()

    private val _error = MutableSingleEventFlow<String>()
    val error: SharedFlow<String> = _error.asSharedFlow()

    fun loadWeather(city: String) = getWeather(city)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(::onResult, ::onError)
        .apply(compositeDisposable::add)



    private fun onResult(weatherAndLocation: WeatherAndLocation) {
        _weatherForToday.value = weatherAndLocation.weather.weatherForToday
        _weatherForDay.value = weatherAndLocation.weather.weatherForDays
        _city.value = weatherAndLocation.location[0].localNames.russian
    }

    private fun onError(throwable: Throwable) {
        _error.tryEmit(ExceptionCatcher.getErrorMessage(Exception(throwable)))
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
