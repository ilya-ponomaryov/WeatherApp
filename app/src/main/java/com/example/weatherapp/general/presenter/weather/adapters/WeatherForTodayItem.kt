package com.example.weatherapp.general.presenter.weather.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.databinding.TodayCardLayoutBinding
import com.example.weatherapp.general.data.weather.models.WeatherForToday
import com.mikepenz.fastadapter.binding.AbstractBindingItem


open class WeatherForTodayItem(private val today: WeatherForToday) :
    AbstractBindingItem<TodayCardLayoutBinding>() {
    override var identifier: Long
        get() = today.hashCode().toLong()
        set(value) {}

    override val type: Int
        get() = R.id.today_weather_layout

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): TodayCardLayoutBinding = TodayCardLayoutBinding.inflate(inflater, parent, false)

    override fun bindView(binding: TodayCardLayoutBinding, payloads: List<Any>) {
        binding.dateTodayCardText.text = today.date
        binding.tempTodayCardText.text = today.temperature
        binding.descTodayCardText.text = today.weatherDescription

        Glide.with(binding.root.context)
            .load(today.weatherIcon)
            .into(binding.iconTodayCardImg)
    }

    override fun unbindView(binding: TodayCardLayoutBinding) {
        binding.dateTodayCardText.text = null
        binding.tempTodayCardText.text = null
        binding.descTodayCardText.text = null
    }
}