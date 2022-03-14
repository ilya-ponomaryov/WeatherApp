package com.example.weatherapp.general.presenter.weather.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.common.DataLocationStatus
import com.example.weatherapp.common.DataWeatherStatus
import com.example.weatherapp.common.utils.ExceptionCatcher
import com.example.weatherapp.general.data.weather.models.WeatherData
import com.example.weatherapp.general.domain.usecases.weather.GetWeatherFromNetworkUseCase
import com.example.weatherapp.general.data.location.models.Location
import com.example.weatherapp.general.data.weather.models.WeatherAndLocation
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

    private val _weatherDataStatusLiveData = MutableLiveData<DataWeatherStatus<WeatherAndLocation>>()
    val weatherDataStatusLiveData: LiveData<DataWeatherStatus<WeatherAndLocation>>
        get() = _weatherDataStatusLiveData

    fun onQueryTextChange(query: String?) {
        if (!query.isNullOrEmpty() && query.isNotBlank()) {
            getWeatherFromNetwork(query)
        } else {
            getWeatherFromNetwork("Тамбов")
        }
    }

    private fun getWeatherFromNetwork(city: String) {
        viewModelScope.launch {
            try {
                val result = getWeatherFromNetworkUseCase.invoke(city)
                _weatherDataStatusLiveData.value = DataWeatherStatus.Success(result)
            } catch (e: Exception) {
                _weatherDataStatusLiveData.value = DataWeatherStatus.Failure(ExceptionCatcher.getErrorMessage(e))
            }

        }
    }

}