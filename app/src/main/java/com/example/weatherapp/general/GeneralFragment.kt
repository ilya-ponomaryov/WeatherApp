package com.example.weatherapp.general

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.weatherapp.common.utils.observe
import com.example.weatherapp.common.utils.showToast
import com.example.weatherapp.databinding.GeneralFragmentBinding
import com.example.weatherapp.dialogs.AddCityDialog
import com.example.weatherapp.general.ui.WeatherForDayItem
import com.example.weatherapp.general.ui.WeatherForTodayItem
import com.mikepenz.fastadapter.*
import com.mikepenz.fastadapter.adapters.GenericItemAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import dagger.hilt.android.AndroidEntryPoint

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

        city?.let { viewModel.loadWeather(it) }
    }

    private fun setupToolbar() {
        binding.searchButton.setOnClickListener {
            AddCityDialog().show(parentFragmentManager)
        }
        viewModel.city.observe(viewLifecycleOwner) { binding.cityName.text = it }
    }

    private fun setupWeatherRecycler() {
        val todayItem = ItemAdapter<WeatherForTodayItem>()
        val dailyItem = GenericItemAdapter()

        val fastAdapter = FastAdapter.with(listOf(todayItem, dailyItem))
        binding.weather.adapter = fastAdapter

        viewModel.weatherForToday.observe(viewLifecycleOwner) {
            todayItem.clear()
            val today = WeatherForTodayItem(it)
            todayItem.add(today)
        }

        viewModel.weatherForDay.observe(viewLifecycleOwner) { weatherForDay ->
            dailyItem.clear()
            val days = weatherForDay.map { WeatherForDayItem(it) }
            dailyItem.add(days)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val ARG_CITY = "city"
    }
}