package com.example.weatherapp.general

import androidx.lifecycle.ViewModel
import com.example.weatherapp.common.utils.ExceptionCatcher
import com.example.weatherapp.common.utils.MutableSingleEventFlow
import com.example.weatherapp.general.usecases.weather.WeatherGetterImpl
import com.example.weatherapp.general.usecases.weather.models.WeatherAndCity
import com.example.weatherapp.general.usecases.weather.models.WeatherForDay
import com.example.weatherapp.general.usecases.weather.models.WeatherForToday
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class GeneralViewModel @Inject constructor(
    private val getWeather: WeatherGetterImpl
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val _weatherForToday = MutableStateFlow<WeatherForToday>(WeatherForToday())
    val weatherForToday: StateFlow<WeatherForToday> = _weatherForToday.asStateFlow()

    private val _weatherForDay = MutableStateFlow<List<WeatherForDay>>(listOf(WeatherForDay()))
    val weatherForDay: StateFlow<List<WeatherForDay>> = _weatherForDay.asStateFlow()

    private val _city = MutableStateFlow<String>("Ваш город")
    val city: StateFlow<String> = _city.asStateFlow()

    private val _error = MutableSingleEventFlow<String>()
    val error: SharedFlow<String> = _error.asSharedFlow()

    fun loadWeather(city: String) = getWeather(city)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(::onResult, ::onError)
        .apply(compositeDisposable::add)



    private fun onResult(weatherAndCity: WeatherAndCity) {
        _weatherForToday.value = weatherAndCity.weather.weatherForToday
        _weatherForDay.value = weatherAndCity.weather.weatherForDays
        _city.value = weatherAndCity.city.cityName
    }

    private fun onError(throwable: Throwable) {
        _error.tryEmit(ExceptionCatcher.getErrorMessage(Exception(throwable)))
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
