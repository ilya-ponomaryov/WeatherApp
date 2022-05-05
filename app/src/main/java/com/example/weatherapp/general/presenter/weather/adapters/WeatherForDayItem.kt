package com.example.weatherapp.general.presenter.weather.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.databinding.WeatherForDayBinding
import com.example.weatherapp.general.usecases.weather.models.WeatherForDay
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class WeatherForDayItem(
    private val weatherForDay: WeatherForDay
) : AbstractBindingItem<WeatherForDayBinding>() {
    override var identifier: Long
        get() = weatherForDay.hashCode().toLong()
        set(value) {}

    override val type: Int
        get() = R.id.weather_for_day_layout

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ) = WeatherForDayBinding.inflate(inflater, parent, false)

    override fun bindView(binding: WeatherForDayBinding, payloads: List<Any>) {
        binding.date.text = weatherForDay.date
        binding.daytimeTemperature.text = weatherForDay.daytimeTemperature
        binding.nighttimeTemperature.text = weatherForDay.nighttimeTemperature
        binding.weatherByHours.adapter = getWeatherByHoursAdapter(weatherForDay)

        Glide.with(binding.root.context)
            .load(weatherForDay.weatherIcon)
            .into(binding.weatherIcon)
    }

    private fun getWeatherByHoursAdapter(
        weatherForDay: WeatherForDay
    ): FastAdapter<WeatherForHourItem> {
        val items = weatherForDay.weatherForHour.map { WeatherForHourItem(it) }
        val itemAdapter = ItemAdapter<WeatherForHourItem>()
        val adapter = FastAdapter.with(itemAdapter)
        itemAdapter.clear()
        itemAdapter.set(items)
        return adapter
    }

    override fun unbindView(binding: WeatherForDayBinding) {
        binding.date.text = null
        binding.daytimeTemperature.text = null
        binding.nighttimeTemperature.text = null
        binding.weatherByHours.adapter = null
    }
}