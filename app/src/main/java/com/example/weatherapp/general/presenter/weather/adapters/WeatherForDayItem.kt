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

class WeatherForDayItem(private val day: WeatherForDay) : AbstractBindingItem<WeatherForDayBinding>() {
    override var identifier: Long
        get() = day.hashCode().toLong()
        set(value) {}

    override val type: Int
        get() = R.id.day_weather_layout

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ) = WeatherForDayBinding.inflate(inflater, parent, false)

    override fun bindView(binding: WeatherForDayBinding, payloads: List<Any>) {
        val itemAdapter = ItemAdapter<WeatherForHourItem>()
        val adapter = FastAdapter.with(itemAdapter)
        itemAdapter.clear()
        itemAdapter.set(getHourlyItems(day.hourly))

        binding.dateDayCardText.text = day.date
        binding.tempDayCardText.text = day.dayTemperature
        binding.tempNightCardText.text = day.nightTemperature
        binding.hourListDayCardRv.adapter = adapter

        Glide.with(binding.root.context)
            .load(day.weatherIcon)
            .into(binding.iconDayCardImg)
    }

    private fun getHourlyItems(data: List<WeatherForHour>): List<WeatherForHourItem> {
        val items = ArrayList<WeatherForHourItem>()
        data.map {
            items.add(WeatherForHourItem(it))
        }
        return items
    }

    override fun unbindView(binding: WeatherForDayBinding) {
        binding.dateDayCardText.text = null
        binding.tempDayCardText.text = null
        binding.tempNightCardText.text = null
        binding.hourListDayCardRv.adapter = null
    }
}