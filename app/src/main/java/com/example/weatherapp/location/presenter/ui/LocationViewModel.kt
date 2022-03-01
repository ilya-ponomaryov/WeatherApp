package com.example.weatherapp.location.presenter.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.location.data.models.Location
import com.example.weatherapp.location.domain.usecases.GetLocationFromNetworkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val getLocationFromNetworkUseCase: GetLocationFromNetworkUseCase
) : ViewModel() {

    private val _locationLiveData = MutableLiveData<Location>()
    val locationLiveData: LiveData<Location>
    get() = _locationLiveData


}