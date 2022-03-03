package com.example.weatherapp.general.presenter.weather.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.common.Result
import com.example.weatherapp.common.utils.Constant
import com.example.weatherapp.general.data.models.weather.WeatherData
import com.example.weatherapp.general.data.models.weather.WeatherRequest
import com.example.weatherapp.general.domain.usecases.weather.GetWeatherFromNetworkUseCase
import com.example.weatherapp.general.data.models.location.Location
import com.example.weatherapp.general.data.models.location.LocationRequest
import com.example.weatherapp.general.domain.usecases.location.GetLocationFromNetworkUseCase
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
    private val weatherRequestLiveData : LiveData<WeatherRequest>
        get() = _weatherRequestLiveData

    private val _locationLiveData = MutableLiveData<Location>()
    private val locationLiveData : LiveData<Location>
        get() = _locationLiveData

    private val _locationRequestLiveData = MutableLiveData<LocationRequest>()
    private val locationRequestLiveData: LiveData<LocationRequest>
        get() = _locationRequestLiveData

    private fun getWeatherFromNetwork(weatherRequest: WeatherRequest) {
        viewModelScope.launch {
            when(val weatherResult =
                getWeatherFromNetworkUseCase.invoke(weatherRequest)) {
                is Result.Loading -> {
                    Log.d("ViewModelLog", "Loading")
                }
                is Result.Success -> {
                    Log.d("ViewModelLog", "Success")
                    _weatherLiveData.value = weatherResult.data!!
                }
                is Result.Error -> {
                    Log.d("ViewModelLog", "Error: ${weatherResult.exception}")
                }
            }
        }
    }

    private fun getLocationFromNetwork() {
        viewModelScope.launch {
            when (val locationResult = getLocationFromNetworkUseCase
                .invoke(locationRequestLiveData.value!!)) {
                is Result.Success ->{
                    Log.d(TAG, "getLocationFromNetwork: Success")
                    _locationLiveData.value = locationResult.data!!
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

    fun onQueryTextChange(query: String) {
        val locationRequest =
            LocationRequest(query, 1, Constant.APPID)
        _locationRequestLiveData.value = locationRequest
        getLocationFromNetwork()
    }

    private fun createWeatherRequest() {
        val location = locationLiveData.value
        if (location != null) {
            _weatherRequestLiveData.value = WeatherRequest(
                location[0].lat,
                location[0].lon,
                "alerts, minutely",
                "metric",
                "ru",
                Constant.APPID
            )
        }

    }

    private fun getWeather() {
        Log.d("GeneralVM", "GetWeather")
        weatherRequestLiveData.value?.let { getWeatherFromNetwork(it) }
        city = locationLiveData.value?.get(0)?.local_names?.ru
    }

    fun onAddCityClick() {
        createWeatherRequest()
        getWeather()
    }



}