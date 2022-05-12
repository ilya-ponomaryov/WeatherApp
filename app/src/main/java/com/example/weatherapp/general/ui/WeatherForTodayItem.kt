package com.example.weatherapp.general.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.databinding.WeatherForTodayBinding
import com.example.weatherapp.general.usecases.weather.models.WeatherForToday
import com.mikepenz.fastadapter.binding.AbstractBindingItem


open class WeatherForTodayItem(
    private val weatherForToday: WeatherForToday
) : AbstractBindingItem<WeatherForTodayBinding>() {
    override var identifier: Long
        get() = weatherForToday.hashCode().toLong()
        set(value) {}

    override val type: Int
        get() = R.id.weather_for_today_layout

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ) = WeatherForTodayBinding.inflate(inflater, parent, false)

    override fun bindView(binding: WeatherForTodayBinding, payloads: List<Any>) {
        binding.date.text = weatherForToday.date
        binding.temperature.text = weatherForToday.temperature
        binding.weatherDescription.text = weatherForToday.weatherDescription

        Glide.with(binding.root.context)
            .load(weatherForToday.weatherIcon)
            .into(binding.weatherIcon)
    }

    override fun unbindView(binding: WeatherForTodayBinding) {
        binding.date.text = null
        binding.temperature.text = null
        binding.weatherDescription.text = null
    }
}