package com.example.weatherapp.general.presenter.weather.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.common.BaseUseCase
import com.example.weatherapp.common.DataLocationStatus
import com.example.weatherapp.common.DataWeatherStatus
import com.example.weatherapp.common.utils.Constant
import com.example.weatherapp.general.data.weather.models.WeatherData
import com.example.weatherapp.general.data.weather.models.WeatherRequest
import com.example.weatherapp.general.domain.usecases.weather.GetWeatherFromNetworkUseCase
import com.example.weatherapp.general.data.location.models.Location
import com.example.weatherapp.general.data.location.models.LocationRequest
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

    private val _weatherRequestLiveData = MutableLiveData<WeatherRequest>()
    private val weatherRequestLiveData : LiveData<WeatherRequest>
        get() = _weatherRequestLiveData

    private val _locationRequestLiveData = MutableLiveData<LocationRequest>()
    private val locationRequestLiveData: LiveData<LocationRequest>
        get() = _locationRequestLiveData

    private val _weatherDataStatusLiveData = MutableLiveData<DataWeatherStatus<WeatherData>>()
    val weatherDataStatusLiveData: LiveData<DataWeatherStatus<WeatherData>>
        get() = _weatherDataStatusLiveData

    private val _locationDataStatusLiveData = MutableLiveData<DataLocationStatus<Location>>()
    val locationDataStatusLiveData: LiveData<DataLocationStatus<Location>>
        get() = _locationDataStatusLiveData

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

    private fun getLocationFromNetwork(locationRequest: LocationRequest) {
        viewModelScope.launch {
            getLocationFromNetworkUseCase.invoke(locationRequest, getLocationFromNetworkUseCaseCallback)
        }
    }

    private val getLocationFromNetworkUseCaseCallback = object : BaseUseCase.Callback<Location> {
        override fun onSuccess(result: Location) {
            _locationDataStatusLiveData.value = DataLocationStatus.Success(result)
            getWeather(result)
            city = result[0].local_names.ru
        }

        override fun onError(throwable: Throwable) {
            _locationDataStatusLiveData.value = DataLocationStatus.Failure(throwable.toString())
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

    private fun getWeatherFromNetwork(weatherRequest: WeatherRequest) {
        viewModelScope.launch {
            Log.d(TAG, "getWeatherFromNetwork: $weatherRequest")
            getWeatherFromNetworkUseCase.invoke(weatherRequest, getWeatherFromNetworkUseCaseCallback)
        }
    }

    private val getWeatherFromNetworkUseCaseCallback = object : BaseUseCase.Callback<WeatherData> {
        override fun onSuccess(result: WeatherData) {
            _weatherDataStatusLiveData.value = DataWeatherStatus.Success(result)
        }

        override fun onError(throwable: Throwable) {
            _weatherDataStatusLiveData.value = DataWeatherStatus.Failure(throwable.toString())
        }
    }

}