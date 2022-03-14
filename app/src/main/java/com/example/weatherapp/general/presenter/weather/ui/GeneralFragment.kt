package com.example.weatherapp.general.presenter.weather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.weatherapp.databinding.GeneralFragmentBinding
import com.example.weatherapp.general.presenter.weather.adapters.GeneralRvAdapter
import com.example.weatherapp.general.presenter.location.ui.AddCityDialog
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class GeneralFragment : Fragment() {
    private val viewModel: GeneralViewModel by viewModels()
    private var _binding: GeneralFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var generalRvAdapter: GeneralRvAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        GeneralFragmentBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        binding.searchToolbarBtn.setOnClickListener {
            showAddCityDialog()
        }
        viewModel.weatherAndLocationDataLiveData.observe(viewLifecycleOwner, Observer { result ->
            generalRvAdapter.getWeatherData(result.weatherData)
            binding.toolbarMainText.text = result.location[0].local_names.ru
        })
        viewModel.errorLiveData.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })
        viewModel.getWeatherFromNetwork(arguments?.getString("city"))
    }

    private fun setupRecyclerView() {
        generalRvAdapter = GeneralRvAdapter(requireContext())
        binding.generalRv.adapter = generalRvAdapter
    }

    private fun showAddCityDialog() {
        val addCityDialog = AddCityDialog()
        addCityDialog.show(parentFragmentManager, "Dialog")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}