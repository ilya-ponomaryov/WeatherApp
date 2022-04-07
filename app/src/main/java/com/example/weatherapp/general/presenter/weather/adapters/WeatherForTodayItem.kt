package com.example.weatherapp.general.presenter.weather.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.databinding.WeatherForTodayBinding
import com.example.weatherapp.general.data.weather.models.WeatherForToday
import com.mikepenz.fastadapter.binding.AbstractBindingItem


open class WeatherForTodayItem(private val today: WeatherForToday) :
    AbstractBindingItem<WeatherForTodayBinding>() {
    override var identifier: Long
        get() = today.hashCode().toLong()
        set(value) {}

    override val type: Int
        get() = R.id.today_weather_layout

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ) = WeatherForTodayBinding.inflate(inflater, parent, false)

    override fun bindView(binding: WeatherForTodayBinding, payloads: List<Any>) {
        binding.date.text = today.date
        binding.temperature.text = today.temperature
        binding.weatherDescription.text = today.weatherDescription

        Glide.with(binding.root.context)
            .load(today.weatherIcon)
            .into(binding.weatherIcon)
    }

    override fun unbindView(binding: WeatherForTodayBinding) {
        binding.date.text = null
        binding.temperature.text = null
        binding.weatherDescription.text = null
    }
}