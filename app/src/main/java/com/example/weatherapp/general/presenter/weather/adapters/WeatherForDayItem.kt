package com.example.weatherapp.general.presenter.weather.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.databinding.DayCardLayoutBinding
import com.example.weatherapp.general.data.weather.models.WeatherForDay
import com.example.weatherapp.general.data.weather.models.WeatherForHour
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import java.util.ArrayList

class WeatherForDayItem(private val daily: WeatherForDay) : AbstractBindingItem<DayCardLayoutBinding>() {
    override var identifier: Long
        get() = daily.hashCode().toLong()
        set(value) {}

    override val type: Int
        get() = R.id.day_weather_layout

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ) = DayCardLayoutBinding.inflate(inflater, parent, false)

    override fun bindView(binding: DayCardLayoutBinding, payloads: List<Any>) {
        val itemAdapter = ItemAdapter<WeatherForHourItem>()
        val adapter = FastAdapter.with(itemAdapter)
        itemAdapter.clear()
        itemAdapter.set(getHourlyItems(daily.hourly))

        binding.dateDayCardText.text = daily.date
        binding.tempDayCardText.text = daily.dayTemperature
        binding.tempNightCardText.text = daily.nightTemperature
        binding.hourListDayCardRv.adapter = adapter

        Glide.with(binding.root.context)
            .load(daily.weatherIcon)
            .into(binding.iconDayCardImg)
    }

    private fun getHourlyItems(data: List<WeatherForHour>): List<WeatherForHourItem> {
        val items = ArrayList<WeatherForHourItem>()
        data.map {
            items.add(WeatherForHourItem(it))
        }
        return items
    }

    override fun unbindView(binding: DayCardLayoutBinding) {
        binding.dateDayCardText.text = null
        binding.tempDayCardText.text = null
        binding.tempNightCardText.text = null
        binding.hourListDayCardRv.adapter = null
    }
}