package com.example.weatherapp.general.presenter.weather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.weatherapp.common.utils.observe
import com.example.weatherapp.common.utils.showToast
import com.example.weatherapp.databinding.GeneralFragmentBinding
import com.example.weatherapp.general.data.weather.models.Hourly
import com.example.weatherapp.general.data.weather.models.WeatherData
import com.example.weatherapp.general.domain.DailyMapper
import com.example.weatherapp.general.domain.HourlyDataConverter
import com.example.weatherapp.general.presenter.location.ui.AddCityDialog
import com.example.weatherapp.general.presenter.weather.adapters.*
import com.mikepenz.fastadapter.*
import com.mikepenz.fastadapter.adapters.GenericItemAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList
import java.util.concurrent.atomic.AtomicLong


@AndroidEntryPoint
class GeneralFragment : Fragment() {
    private val viewModel: GeneralViewModel by viewModels()

    private var _binding: GeneralFragmentBinding? = null
    private val binding get() = _binding!!

    private val city get() = arguments?.getString(ARG_CITY)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = GeneralFragmentBinding.inflate(inflater, container, false)
        .also { _binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        setupWeatherRecycler()

        viewModel.error.observe(viewLifecycleOwner) { showToast(it) }

        viewModel.loadWeather(city)
    }

    private fun setupToolbar() {
        binding.selectCity.setOnClickListener {
            AddCityDialog().show(parentFragmentManager)
        }
        viewModel.city.observe(viewLifecycleOwner) { binding.city.text = it }
    }

    private fun setupWeatherRecycler() {
        val todayItem = ItemAdapter<TodayWeatherItem>()
        val dailyItem = GenericItemAdapter()

        val fastAdapter = FastAdapter.with(listOf(todayItem, dailyItem))
        binding.weather.adapter = fastAdapter

        viewModel.weather.observe(viewLifecycleOwner) { weatherData ->
            todayItem.clear()
            dailyItem.clear()

            val today = TodayWeatherItem(weatherData.current)
            today.identifier = 0

            todayItem.add(today)
            dailyItem.add(getDailyItems(weatherData))
        }
    }

    private fun getDailyItems(data: WeatherData): List<DailyWeatherItem> {
        val dailyMapper = DailyMapper(data.daily, data.hourly)
        val dailyEquipped = dailyMapper.toDailyEquippedList()
        val items = ArrayList<DailyWeatherItem>()
        var position = 0
        val id = AtomicLong(1)

        while (position < 5) {
            val daily = DailyWeatherItem(dailyEquipped[position])
            daily.identifier = id.getAndIncrement()
            items.add(daily)

            position++
        }
        return items
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val ARG_CITY = "city"
    }
}