package com.example.weatherapp.general.presenter.weather.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.databinding.DayCardLayoutBinding
import com.example.weatherapp.general.data.weather.models.Daily
import com.example.weatherapp.general.data.weather.models.DailyEquipped
import com.example.weatherapp.general.data.weather.models.Hourly
import com.example.weatherapp.general.domain.DateConverter
import com.example.weatherapp.general.domain.HourlyDataConverter
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import java.util.ArrayList

class DailyWeatherItem(private val daily: DailyEquipped) : AbstractBindingItem<DayCardLayoutBinding>() {
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
        itemAdapter.set(getHourlyItems(daily.hourly))

        binding.dateDayCardText.text = daily.date
        binding.tempDayCardText.text = daily.dayTemperature
        binding.tempNightCardText.text = daily.nightTemperature
        binding.hourListDayCardRv.adapter = adapter

        Glide.with(binding.root.context)
            .load("http://openweathermap.org/img/w/" + daily.weather[0].icon + ".png")
            .into(binding.iconDayCardImg)
    }

    private fun getHourlyItems(data: List<Hourly>): List<HourlyWeatherItem> {
        val items = ArrayList<HourlyWeatherItem>()
        data.forEach {
            items.add(HourlyWeatherItem(it))
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