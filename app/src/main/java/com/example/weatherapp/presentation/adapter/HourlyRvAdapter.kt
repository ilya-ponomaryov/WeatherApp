package com.example.weatherapp.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.data.models.Hourly
import com.example.weatherapp.databinding.HourCardLayoutBinding
import java.util.*
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes

class HourlyRvAdapter(private val context: Context) : RecyclerView.Adapter<HourlyRvAdapter.ViewHolder>() {
    private val hourlyList = arrayListOf<Hourly>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = HourCardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(hourlyList[position])
    }

    override fun getItemCount(): Int = 5

    inner class ViewHolder(private val binding: HourCardLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Hourly) {
            val date = Date(item.dt.toLong() * 1000)
            binding.tempHourCardText.text = item.temp.toInt().toString() + "°"
            binding.timeHourCardText.text = date.hours.toString() + ":" + date.minutes + "0"
            Glide.with(context)
                .load("http://openweathermap.org/img/w/" + item.weather[0].icon + ".png")
                .into(binding.iconHourCardImg)
        }
    }

    fun getHourlyData(data: List<Hourly>) {
        hourlyList.clear()
        hourlyList.addAll(data)
    }
}