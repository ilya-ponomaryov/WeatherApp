package com.example.weatherapp.general.presenter.weather.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.databinding.HourCardLayoutBinding
import com.example.weatherapp.general.data.weather.models.HourlyEquipped
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class WeatherForHourItem(private val hourly: HourlyEquipped) : AbstractBindingItem<HourCardLayoutBinding>(){
    override val type: Int
        get() = R.id.hour_weather_layout

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ) = HourCardLayoutBinding.inflate(inflater, parent, false)

    override fun bindView(binding: HourCardLayoutBinding, payloads: List<Any>) {
        binding.tempHourCardText.text = hourly.temperature
        binding.timeHourCardText.text = hourly.date
        Glide.with(binding.root.context)
            .load(hourly.weatherIcon)
            .into(binding.iconHourCardImg)
    }

    override fun unbindView(binding: HourCardLayoutBinding) {
        super.unbindView(binding)
        binding.tempHourCardText.text = null
        binding.timeHourCardText.text = null
    }
}