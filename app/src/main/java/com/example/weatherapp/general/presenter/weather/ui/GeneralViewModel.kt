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
            Log.d(TAG, "getWeatherFromNetwork: $weatherRequest")
            when(val weatherResult =
                getWeatherFromNetworkUseCase.invoke(weatherRequest)) {
                is Result.Loading -> {
                    Log.d(TAG, "getWeatherFromNetwork: Loading")
                }
                is Result.Success -> {
                    Log.d(TAG, "getWeatherFromNetwork: Success")
                    _weatherLiveData.value = weatherResult.data!!
                }
                is Result.Error -> {
                    Log.d(TAG, "getWeatherFromNetwork: Error: ${weatherResult.exception}")
                }
            }
        }
    }

    private fun getLocationFromNetwork(locationRequest: LocationRequest) {
        viewModelScope.launch {
            when (val locationResult = getLocationFromNetworkUseCase
                .invoke(locationRequest)) {
                is Result.Success ->{
                    Log.d(TAG, "getLocationFromNetwork: Success")
                    _locationLiveData.value = locationResult.data!!
                    city = locationResult.data[0].local_names.ru
                    getWeather(locationResult.data)
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

    fun onQueryTextChange(query: String?) {
        if (!query.isNullOrEmpty() && query.isNotBlank()) {
            Log.d(TAG, "onQueryTextChange not null $query")
            val locationRequest =
                LocationRequest(query, 1, Constant.APPID)
            getLocationFromNetwork(locationRequest)
        } else {
            city = "Тамбов"
            val locationRequest =
                LocationRequest(city!!, 1, Constant.APPID)
            _locationRequestLiveData.value = locationRequest
            getLocationFromNetwork(locationRequest)
        }
    }

    private fun createWeatherRequest(location: Location): WeatherRequest {
        Log.d(TAG, "createWeatherRequest")
            return WeatherRequest(
                location[0].lat,
                location[0].lon,
                "alerts, minutely",
                "metric",
                "ru",
                Constant.APPID)
    }

    private fun getWeather(location: Location) {
        Log.d(TAG, "GetWeather")
        getWeatherFromNetwork(createWeatherRequest(location))
    }





}