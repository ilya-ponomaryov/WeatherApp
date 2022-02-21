package com.example.weatherapp.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.data.models.WeatherData
import com.example.weatherapp.databinding.GeneralTodayFragmentBinding
import java.util.*

class GeneralTodayFragment : Fragment() {
    private val viewModel: GeneralTodayViewModel by viewModels()
    private var _binding: GeneralTodayFragmentBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = GeneralTodayFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = GeneralTodayFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharingViewModel = ViewModelProvider(requireActivity()).get(SharingViewModel::class.java)
        sharingViewModel.shareLiveData.observe(viewLifecycleOwner, Observer {
            viewModel.setWeatherData(it as WeatherData)
        })
        viewModel.weatherLiveData.observe(viewLifecycleOwner, Observer {
            val date = Date(it.current.dt.toLong() * 1000)
            binding.dateTodayExpandedCardText.text = date.toLocaleString()
            binding.descTodayExpandedCardText.text = it.current.weather[0].description +
                    ", ощущается как " + it.current.feels_like.toInt().toString()
            binding.humidityTodayExpandedCardText.text = it.current.humidity.toString()
            //binding.precipitationTodayExpandedCardText.text = it.sn
            binding.tempTodayExpandedCardText.text = it.current.temp.toInt().toString()
            binding.windTodayExpandedCardText.text = it.current.wind_speed.toInt().toString()
            Glide.with(this)
                .load("http://openweathermap.org/img/w/" + it.current.weather[0].icon + ".png")
                .into(binding.iconTodayCardImg)
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}