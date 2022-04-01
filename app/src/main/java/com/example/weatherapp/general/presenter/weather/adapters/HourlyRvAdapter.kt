package com.example.weatherapp.location.presenter.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.databinding.WeatherForHourBinding
import com.example.weatherapp.general.data.weather.models.Hourly
import java.util.*

class HourlyRvAdapter(private val context: Context) : RecyclerView.Adapter<HourlyRvAdapter.ViewHolder>() {
    private val hourlyList = arrayListOf<Hourly>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = WeatherForHourBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(hourlyList[position])
    }

    override fun getItemCount(): Int = hourlyList.size

    inner class ViewHolder(private val binding: WeatherForHourBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Hourly) {
            val date = Date(item.date.toLong() * 1000)
            binding.temperature.text = item.temperature.toInt().toString() + "°"
            binding.hour.text = date.hours.toString() + ":" + date.minutes + "0"
            Glide.with(context)
                .load("http://openweathermap.org/img/w/" + item.weather[0].icon + ".png")
                .into(binding.weatherIcon)
        }
    }

    fun getHourlyData(data: List<Hourly>) {
        hourlyList.clear()
        hourlyList.addAll(data)
    }
}