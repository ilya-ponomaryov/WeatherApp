package com.example.weatherapp.general.presenter.weather.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.general.data.weather.models.Current
import com.example.weatherapp.general.data.weather.models.Daily
import com.example.weatherapp.general.data.weather.models.Hourly
import com.example.weatherapp.general.data.weather.models.WeatherData
import com.example.weatherapp.databinding.DayCardLayoutBinding
import com.example.weatherapp.databinding.WeatherForTodayBinding
import com.example.weatherapp.general.domain.DateConverter
import com.example.weatherapp.general.domain.HourlyDataConverter
import com.example.weatherapp.location.presenter.adapters.HourlyRvAdapter

class GeneralRvAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val weatherDataList = arrayListOf<WeatherData>()

    private val hourList = arrayListOf<List<Hourly>>()

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            0
        } else {
            1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            0 -> {
                val view = WeatherForTodayBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return TodayViewHolder(view)
            }
            1 -> {
                val view = DayCardLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return DayViewHolder(view)
            }
            else -> {
                throw RuntimeException("View creating has been failed")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            0 -> initTodayViewHolder(holder as TodayViewHolder)
            1 -> initDayViewHolder(holder as DayViewHolder, position)
        }
    }

    private fun initTodayViewHolder(holder: TodayViewHolder) {
        if (weatherDataList.isNotEmpty())
            holder.bind(weatherDataList[0].current)
    }

    private fun initDayViewHolder(holder: DayViewHolder, position: Int) {
        if (weatherDataList.isNotEmpty())
            holder.bind(weatherDataList[0].daily[position], position)
    }

    override fun getItemCount(): Int = 5

    inner class TodayViewHolder(private val binding: WeatherForTodayBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(current: Current) {
            val converter = DateConverter()

            binding.date.text = "Сегодня, " + converter.getDateAsString(current.date)
            binding.temperature.text = current.temperature.toInt().toString() + "°"
            binding.description.text =
                current.weather[0].description +
                        ", ощущается как " + current.feelsLike.toInt().toString()

            Glide.with(binding.root.context)
                .load("http://openweathermap.org/img/w/" + current.weather[0].icon + ".png")
                .into(binding.icon)

        }

    }

    inner class DayViewHolder(private val binding: DayCardLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(daily: Daily, p: Int) {
            val adapter = HourlyRvAdapter(binding.root.context)

            val dataConverter = HourlyDataConverter()

            hourList.clear()
            hourList.addAll(dataConverter.getHourlyData(weatherDataList[0].hourly))
            adapter.getHourlyData(hourList[p - 1])

            val converter = DateConverter()

            binding.dateDayCardText.text = converter.getDateAsString(daily.dt)
            binding.tempDayCardText.text = daily.temp.day.toInt().toString() + "°"
            binding.tempNightCardText.text = daily.temp.night.toInt().toString() + "°"
            binding.hourListDayCardRv.adapter = adapter

            Glide.with(binding.root.context)
                .load("http://openweathermap.org/img/w/" + daily.weather[0].icon + ".png")
                .into(binding.iconDayCardImg)
        }
    }

    fun setWeather(data: WeatherData) {
        weatherDataList.clear()
        weatherDataList.add(data)
        notifyDataSetChanged()
    }
}