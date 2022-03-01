package com.example.weatherapp.general.presenter.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.common.Result
import com.example.weatherapp.general.data.models.WeatherData
import com.example.weatherapp.general.data.models.WeatherRequest
import com.example.weatherapp.general.domain.usecases.GetWeatherFromNetworkUseCase
import com.example.weatherapp.location.data.models.Location
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

    private val _weatherRequestLiveData = MutableLiveData<WeatherRequest>()
    val weatherRequestLiveData : LiveData<WeatherRequest>
        get() = _weatherRequestLiveData

    private val _locationLiveData = MutableLiveData<Location>()
    val locationLiveData : LiveData<Location>
        get() = _locationLiveData

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

    fun setWeatherRequest(data: WeatherRequest) {
        _weatherRequestLiveData.value = data
    }

    fun setLocation(data: Location) {
        _locationLiveData.value = data
    }

}