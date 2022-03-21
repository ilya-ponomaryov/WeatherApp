package com.example.weatherapp.general.presenter.weather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.weatherapp.common.utils.showToast
import com.example.weatherapp.databinding.GeneralFragmentBinding
import com.example.weatherapp.general.presenter.weather.adapters.GeneralRvAdapter
import com.example.weatherapp.general.presenter.location.ui.AddCityDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


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

        lifecycleScope.launchWhenCreated {
            viewModel.error.collect { error ->
                error?.let { showToast(it) }
            }
        }

        viewModel.loadWeather(city)
    }

    private fun setupToolbar() {
        binding.selectCity.setOnClickListener {
            AddCityDialog().show(parentFragmentManager)
        }

        lifecycleScope.launchWhenCreated {
            viewModel.city.collect { city ->
                city?.let { binding.city.text = it }
            }
        }
    }

    private fun setupWeatherRecycler() {
        binding.weather.adapter = weatherAdapter

        lifecycleScope.launchWhenCreated {
            viewModel.weather.collect { weather ->
                weather?.let { weatherAdapter.setWeather(it) }
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