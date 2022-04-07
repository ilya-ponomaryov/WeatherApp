package com.example.weatherapp.general.presenter.weather.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.databinding.WeatherForDayBinding
import com.example.weatherapp.general.data.weather.models.WeatherForDay
import com.example.weatherapp.general.data.weather.models.WeatherForHour
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import java.util.ArrayList

class WeatherForDayItem(private val weatherForDay: WeatherForDay) : AbstractBindingItem<WeatherForDayBinding>() {
    override var identifier: Long
        get() = weatherForDay.hashCode().toLong()
        set(value) {}

    override val type: Int
        get() = R.id.day_weather_layout

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ) = WeatherForDayBinding.inflate(inflater, parent, false)

    override fun bindView(binding: WeatherForDayBinding, payloads: List<Any>) {
        val items = weatherForDay.hourly.map { WeatherForHourItem(it) }
        val itemAdapter = ItemAdapter<WeatherForHourItem>()
        val adapter = FastAdapter.with(itemAdapter)
        itemAdapter.clear()
        itemAdapter.set(items)

        binding.date.text = weatherForDay.date
        binding.daytimeTemperature.text = weatherForDay.dayTemperature
        binding.nighttimeTemperature.text = weatherForDay.nightTemperature
        binding.weatherByHours.adapter = adapter

        Glide.with(binding.root.context)
            .load(weatherForDay.weatherIcon)
            .into(binding.weatherIcon)
    }

    override fun unbindView(binding: WeatherForDayBinding) {
        binding.date.text = null
        binding.daytimeTemperature.text = null
        binding.nighttimeTemperature.text = null
        binding.weatherByHours.adapter = null
    }
}