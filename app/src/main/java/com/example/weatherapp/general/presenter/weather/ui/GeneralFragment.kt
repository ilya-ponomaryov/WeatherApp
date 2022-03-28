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
import com.example.weatherapp.general.presenter.weather.adapters.GeneralRvAdapter
import com.example.weatherapp.general.presenter.location.ui.AddCityDialog
import com.example.weatherapp.general.presenter.weather.adapters.DailyWeatherItem
import com.example.weatherapp.general.presenter.weather.adapters.TodayWeatherItem
import com.mikepenz.fastadapter.*
import com.mikepenz.fastadapter.adapters.GenericItemAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class GeneralFragment : Fragment() {
    private val viewModel: GeneralViewModel by viewModels()

    private var _binding: GeneralFragmentBinding? = null
    private val binding get() = _binding!!

    private val weatherAdapter = GeneralRvAdapter()

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
        viewModel.weather.observe(viewLifecycleOwner) {
            todayItem.clear()
            dailyItem.clear()
            todayItem.add(0, TodayWeatherItem(it.current))
            it.daily.forEach { daily ->
                dailyItem.add(DailyWeatherItem(daily))

            }

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