package com.example.weatherapp.general.presenter.weather.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.databinding.DayCardLayoutBinding
import com.example.weatherapp.general.data.weather.models.Daily
import com.example.weatherapp.general.data.weather.models.WeatherData
import com.example.weatherapp.general.domain.DateConverter
import com.example.weatherapp.general.domain.HourlyDataConverter
import com.example.weatherapp.location.presenter.adapters.HourlyRvAdapter
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class DailyWeatherItem(private val daily: Daily) : AbstractBindingItem<DayCardLayoutBinding>() {
    private val weatherDataList = arrayListOf<WeatherData>()
    override val type: Int
        get() = R.id.weather

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ) = DayCardLayoutBinding.inflate(inflater, parent, false)

    override fun bindView(binding: DayCardLayoutBinding, payloads: List<Any>) {
        val adapter = HourlyRvAdapter(binding.root.context)

        val c = HourlyDataConverter()

        //hourList.clear()
        //hourList.addAll(c.getHourlyData(weatherDataList[0].hourly))
        //adapter.getHourlyData(hourList[p - 1])

        val converter = DateConverter()

        binding.dateDayCardText.text = converter.getDateAsString(daily.dt)
        binding.tempDayCardText.text = daily.temp.day.toInt().toString() + "°"
        binding.tempNightCardText.text = daily.temp.night.toInt().toString() + "°"
        //binding.hourListDayCardRv.adapter = adapter

        Glide.with(binding.root.context)
            .load("http://openweathermap.org/img/w/" + daily.weather[0].icon + ".png")
            .into(binding.iconDayCardImg)
    }

    override fun unbindView(binding: DayCardLayoutBinding) {
        binding.dateDayCardText.text = null
        binding.tempDayCardText.text = null
        binding.tempNightCardText.text = null

    }

    fun getData(data: WeatherData) {
        weatherDataList.clear()
        weatherDataList.add(data)
    }
}