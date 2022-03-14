package com.example.weatherapp.general.presenter.weather.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.common.DataLocationStatus
import com.example.weatherapp.common.DataWeatherStatus
import com.example.weatherapp.databinding.GeneralFragmentBinding
import com.example.weatherapp.general.presenter.weather.adapters.GeneralRvAdapter
import com.example.weatherapp.general.presenter.location.ui.AddCityDialog
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class GeneralFragment : Fragment() {
    private val viewModel: GeneralViewModel by viewModels()
    private var _binding: GeneralFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var generalRvAdapter: GeneralRvAdapter
    private val navController: NavController get() = findNavController()

    companion object {
        fun newInstance() = GeneralFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        GeneralFragmentBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.generalRv
        setupRecyclerView()
        val city = arguments?.getString("city")
        Log.d("GeneralFragmentLog", "City: $city")
        viewModel.onQueryTextChange(city)
        binding.searchToolbarBtn.setOnClickListener {
            val addCityDialog = AddCityDialog()
            fragmentManager?.let { it1 -> addCityDialog.show(it1, "Dialog") }

        }
        viewModel.weatherDataStatusLiveData.observe(viewLifecycleOwner, Observer { result ->
            when(result) {
                is DataWeatherStatus.Success -> {
                    Log.d("GeneralFragmentLog", result.data.weatherData.current.temp.toString())
                    generalRvAdapter.getWeatherData(result.data.weatherData)
                    binding.toolbarMainText.text = result.data.location[0].local_names.ru
                }
                is DataWeatherStatus.Failure -> {
                    Toast.makeText(requireContext(), result.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun setupRecyclerView() {
        generalRvAdapter = GeneralRvAdapter(requireContext())
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = generalRvAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}