package com.example.weatherapp.general.presenter.weather.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.databinding.HourCardLayoutBinding
import com.example.weatherapp.general.data.weather.models.Hourly
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import java.util.*

class HourlyWeatherItem(private val hourly: Hourly) : AbstractBindingItem<HourCardLayoutBinding>(){
    override val type: Int
        get() = R.id.hour_weather_layout

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ) = HourCardLayoutBinding.inflate(inflater, parent, false)

    override fun bindView(binding: HourCardLayoutBinding, payloads: List<Any>) {
        val date = Date(hourly.dt.toLong() * 1000)
        binding.tempHourCardText.text = hourly.temp.toInt().toString() + "°"
        binding.timeHourCardText.text = date.hours.toString() + ":" + date.minutes + "0"
        Glide.with(binding.root.context)
            .load("http://openweathermap.org/img/w/" + hourly.weather[0].icon + ".png")
            .into(binding.iconHourCardImg)
    }

    override fun unbindView(binding: HourCardLayoutBinding) {
        super.unbindView(binding)
        binding.tempHourCardText.text = null
        binding.timeHourCardText.text = null
    }
}