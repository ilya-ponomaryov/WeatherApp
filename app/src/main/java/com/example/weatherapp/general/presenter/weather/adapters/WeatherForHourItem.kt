package com.example.weatherapp.general.presenter.weather.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.databinding.WeatherForHourBinding
import com.example.weatherapp.general.data.weather.models.WeatherForHour
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class WeatherForHourItem(
    private val weatherForHour: WeatherForHour
) : AbstractBindingItem<WeatherForHourBinding>() {
    override var identifier: Long
        get() = weatherForHour.hashCode().toLong()
        set(value) {}

    override val type: Int
        get() = R.id.weather_for_hour_layout

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ) = WeatherForHourBinding.inflate(inflater, parent, false)

    override fun bindView(binding: WeatherForHourBinding, payloads: List<Any>) {
        binding.temperature.text = weatherForHour.temperature
        binding.hour.text = weatherForHour.hour
        Glide.with(binding.root.context)
            .load(weatherForHour.weatherIcon)
            .into(binding.weatherIcon)
    }

    override fun unbindView(binding: WeatherForHourBinding) {
        super.unbindView(binding)
        binding.temperature.text = null
        binding.hour.text = null
    }
}