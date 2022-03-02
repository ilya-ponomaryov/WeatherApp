package com.example.weatherapp.general.presenter.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.common.Result
import com.example.weatherapp.common.utils.Constant
import com.example.weatherapp.general.data.models.WeatherData
import com.example.weatherapp.general.data.models.WeatherRequest
import com.example.weatherapp.general.domain.usecases.GetWeatherFromNetworkUseCase
import com.example.weatherapp.location.data.models.Location
import com.example.weatherapp.location.data.models.LocationRequest
import com.example.weatherapp.location.domain.usecases.GetLocationFromNetworkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GeneralViewModel @Inject constructor(
    private val getWeatherFromNetworkUseCase: GetWeatherFromNetworkUseCase,
    private val getLocationFromNetworkUseCase: GetLocationFromNetworkUseCase
) : ViewModel() {
    private val TAG = "GeneralVM"
    var city: String? = null
    init {
        getWeatherFromNetwork(
            WeatherRequest(
            52.731118499999994,
            41.434199146086904,
            "minutely,alerts",
            "metric",
            "ru",
            Constant.APPID
        )
        )
        city = "Тамбов"
        Log.d(TAG, "init")
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

    fun getWeatherFromNetwork(weatherRequest: WeatherRequest) {
        viewModelScope.launch {
            when(val weatherResult =
                getWeatherFromNetworkUseCase.invoke(weatherRequest)) {
                is Result.Loading -> {
                    Log.d("ViewModelLog", "Loading")
                }
                is Result.Success -> {
                    Log.d("ViewModelLog", "Success")
                    setWeather(weatherResult.data!!)
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

    fun setWeather(data: WeatherData) {
        _weatherLiveData.value = data
    }

    private val _locationRequestLiveData = MutableLiveData<LocationRequest>()
    val locationRequestLiveData: LiveData<LocationRequest>
        get() = _locationRequestLiveData


    fun getLocationFromNetwork() {
        viewModelScope.launch {
            when (val locationResult = getLocationFromNetworkUseCase
                .invoke(locationRequestLiveData.value!!)) {
                is Result.Success ->{
                    Log.d(TAG, "getLocationFromNetwork: Success")
                    setLocation(locationResult.data!!)
                }
                is Result.Loading -> {
                    Log.d(TAG, "getLocationFromNetwork: Loading")
                }
                is Result.Error -> {
                    Log.d(TAG, "getLocationFromNetwork: Error, ${locationResult.exception}")
                }
            }
        }
    }

    fun setLocationRequest(data: LocationRequest) {
        _locationRequestLiveData.value = data
    }

    fun createWeatherRequest(data: Location) {
        setWeatherRequest(WeatherRequest(
            data[0].lat,
            data[0].lon,
            "alerts, minutely",
            "metric",
            "ru",
            Constant.APPID))

    }

    fun getWeather() {
        Log.d("GeneralVM", "GetWeather")
        weatherRequestLiveData.value?.let { getWeatherFromNetwork(it) }
        city = locationLiveData.value?.get(0)?.local_names?.ru
    }



}