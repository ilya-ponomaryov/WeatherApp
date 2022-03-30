package com.example.weatherapp.general.presenter.weather.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.databinding.DayCardLayoutBinding
import com.example.weatherapp.general.data.weather.models.Daily
import com.example.weatherapp.general.domain.DateConverter
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import java.util.ArrayList

class DailyWeatherItem(private val daily: Daily) : AbstractBindingItem<DayCardLayoutBinding>() {
    private val hourlyItems = ArrayList<HourlyWeatherItem>()
    override val type: Int
        get() = R.id.day_weather_layout

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ) = DayCardLayoutBinding.inflate(inflater, parent, false)

    override fun bindView(binding: DayCardLayoutBinding, payloads: List<Any>) {
        val itemAdapter = ItemAdapter<HourlyWeatherItem>()
        val adapter = FastAdapter.with(itemAdapter)
        itemAdapter.clear()
        itemAdapter.set(hourlyItems)

        val converter = DateConverter()

        binding.dateDayCardText.text = converter.getDateAsString(daily.dt)
        binding.tempDayCardText.text = daily.temp.day.toInt().toString() + "°"
        binding.tempNightCardText.text = daily.temp.night.toInt().toString() + "°"
        binding.hourListDayCardRv.adapter = adapter

        Glide.with(binding.root.context)
            .load("http://openweathermap.org/img/w/" + daily.weather[0].icon + ".png")
            .into(binding.iconDayCardImg)
    }

    override fun unbindView(binding: DayCardLayoutBinding) {
        binding.dateDayCardText.text = null
        binding.tempDayCardText.text = null
        binding.tempNightCardText.text = null
        binding.hourListDayCardRv.adapter = null
    }

    fun getHourlyItems(data: List<HourlyWeatherItem>) {
        hourlyItems.clear()
        hourlyItems.addAll(data)
    }
}