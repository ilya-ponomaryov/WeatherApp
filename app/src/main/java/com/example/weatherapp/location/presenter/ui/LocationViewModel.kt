package com.example.weatherapp.location.presenter.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.common.Result
import com.example.weatherapp.location.data.models.Location
import com.example.weatherapp.location.data.models.LocationRequest
import com.example.weatherapp.location.domain.usecases.GetLocationFromNetworkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val getLocationFromNetworkUseCase: GetLocationFromNetworkUseCase
) : ViewModel() {
    private val TAG = "CityVM"

    private val _locationLiveData = MutableLiveData<Location>()
    val locationLiveData: LiveData<Location>
    get() = _locationLiveData

    private val _locationRequestLiveData = MutableLiveData<LocationRequest>()
    val locationRequestLiveData: LiveData<LocationRequest>
        get() = _locationRequestLiveData


    fun getLocationFromNetwork() {
        viewModelScope.launch {
            when (val locationResult = getLocationFromNetworkUseCase
                .invoke(LocationRequest("Moscow", 1, "b4ffacf20b03eabce37d537a7d83a675"))) {
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
}