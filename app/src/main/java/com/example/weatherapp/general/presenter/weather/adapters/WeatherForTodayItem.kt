package com.example.weatherapp.general.presenter.weather.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.databinding.TodayCardLayoutBinding
import com.example.weatherapp.general.data.weather.models.Current
import com.example.weatherapp.general.domain.DateConverter
import com.mikepenz.fastadapter.binding.AbstractBindingItem


open class WeatherForTodayItem(private val current: Current) :
    AbstractBindingItem<TodayCardLayoutBinding>() {
    override val type: Int
        get() = R.id.today_weather_layout

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): TodayCardLayoutBinding = TodayCardLayoutBinding.inflate(inflater, parent, false)

    override fun bindView(binding: TodayCardLayoutBinding, payloads: List<Any>) {
        val converter = DateConverter()

        binding.dateTodayCardText.text = "Сегодня, " + converter.getDateAsString(current.dt)
        binding.tempTodayCardText.text = current.temp.toInt().toString() + "°"
        binding.descTodayCardText.text =
            current.weather[0].description +
                    ", ощущается как " + current.feels_like.toInt().toString()

        Glide.with(binding.root.context)
            .load("http://openweathermap.org/img/w/" + current.weather[0].icon + ".png")
            .into(binding.iconTodayCardImg)
    }

    override fun unbindView(binding: TodayCardLayoutBinding) {
        binding.dateTodayCardText.text = null
        binding.tempTodayCardText.text = null
        binding.descTodayCardText.text = null
    }
}