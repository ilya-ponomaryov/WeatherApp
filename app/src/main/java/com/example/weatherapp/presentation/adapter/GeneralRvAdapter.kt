package com.example.weatherapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.data.models.Current
import com.example.weatherapp.data.models.Daily
import com.example.weatherapp.data.models.WeatherData
import com.example.weatherapp.databinding.DayCardLayoutBinding
import com.example.weatherapp.databinding.TodayCardLayoutBinding

class GeneralRvAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var weatherData: WeatherData

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            0
        } else {
            1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType) {
            0 -> {
                val view = TodayCardLayoutBinding.inflate(
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
        when(holder.itemViewType) {
            0 -> initTodayViewHolder(holder as TodayViewHolder)
            1 -> initDayViewHolder(holder as DayViewHolder, position)
        }
    }

    private fun initTodayViewHolder(holder: TodayViewHolder) {
        holder.bind(weatherData.current)
    }

    private fun initDayViewHolder(holder: DayViewHolder, position: Int) {
        holder.bind(weatherData.daily[position])
    }

    override fun getItemCount(): Int = 5

    inner class TodayViewHolder(private val binding: TodayCardLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(current: Current) {
            binding.dateTodayCardText.text = "00.00.00"
            binding.tempTodayCardText.text = current.temp.toInt().toString()
            binding.descTodayCardText.text = current.weather[0].main + "ощущается как" + current.feels_like.toInt().toString()
        }

    }

    inner class DayViewHolder(private val binding: DayCardLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(daily: Daily) {
            binding.dateDayCardText.text = "00.00.00"
            binding.tempDayCardText.text = daily.temp.day.toInt().toString()
            binding.tempNightCardText.text = daily.temp.night.toInt().toString()
        }
    }

    fun getWeatherData(data: WeatherData) {
        weatherData = data
    }
}