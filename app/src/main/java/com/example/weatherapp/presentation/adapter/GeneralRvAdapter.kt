package com.example.weatherapp.presentation.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.data.models.Current
import com.example.weatherapp.data.models.Daily
import com.example.weatherapp.data.models.Hourly
import com.example.weatherapp.data.models.WeatherData
import com.example.weatherapp.databinding.DayCardLayoutBinding
import com.example.weatherapp.databinding.TodayCardLayoutBinding
import com.example.weatherapp.domain.DateConverter
import com.example.weatherapp.domain.HourlyDataConverter

class GeneralRvAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
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
        holder.bind(weatherDataList[0].current)
    }

    private fun initDayViewHolder(holder: DayViewHolder, position: Int) {
        holder.bind(weatherDataList[0].daily[position], position)
    }

    override fun getItemCount(): Int = 5

    inner class TodayViewHolder(private val binding: TodayCardLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(current: Current) {
            val converter = DateConverter()
            binding.dateTodayCardText.text = "Сегодня, "+ converter.getDateAsString(current.dt)
            binding.tempTodayCardText.text = current.temp.toInt().toString() + "°"
            binding.descTodayCardText.text=
                current.weather[0].description + ", ощущается как " + current.feels_like.toInt().toString()

            Glide.with(context)
                .load("http://openweathermap.org/img/w/" + current.weather[0].icon + ".png")
                .into(binding.iconTodayCardImg)
            Log.d("Image", current.weather[0].icon)

        }

    }

    inner class DayViewHolder(private val binding: DayCardLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(daily: Daily, p: Int) {
            val adapter = HourlyRvAdapter(context)
            //adapter.getHourlyData(weatherDataList[0].hourly)
            val c = HourlyDataConverter()
            hourList.addAll(c.getHourlyData(weatherDataList[0].hourly))
                adapter.getHourlyData(hourList[p-1])
            val converter = DateConverter()
            binding.dateDayCardText.text = converter.getDateAsString(daily.dt)
            binding.tempDayCardText.text = daily.temp.day.toInt().toString() + "°"
            binding.tempNightCardText.text = daily.temp.night.toInt().toString() + "°"
            binding.hourListDayCardRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            binding.hourListDayCardRv.adapter = adapter
            Glide.with(context)
                .load("http://openweathermap.org/img/w/" + daily.weather[0].icon + ".png")
                .into(binding.iconDayCardImg)
        }
    }

    fun getWeatherData(data: WeatherData) {
        weatherDataList.clear()
        weatherDataList.add(data)
    }
}