package com.example.weatherapp.general.presenter.weather.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.GeneralFragmentBinding
import com.example.weatherapp.general.presenter.weather.adapters.GeneralRvAdapter
import com.example.weatherapp.general.presenter.location.ui.AddCityDialog
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class GeneralFragment : Fragment() {
    private val viewModel: GeneralViewModel by activityViewModels()
    private var _binding: GeneralFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var generalRvAdapter: GeneralRvAdapter
    private val navController: NavController get() = findNavController()

    companion object {
        fun newInstance() = GeneralFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = GeneralFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val city = arguments?.getString("city")
        Log.d("GeneralFragmentLog", "City: $city")
        viewModel.onQueryTextChange(city)
        recyclerView = binding.generalRv
        binding.searchToolbarBtn.setOnClickListener {
            val addCityDialog = AddCityDialog()
            fragmentManager?.let { it1 -> addCityDialog.show(it1, "Dialog") }

        }
        viewModel.weatherLiveData.observe(viewLifecycleOwner, Observer { data ->
            Log.d("GeneralFragmentLog", data.current.temp.toInt().toString())
            setupRecyclerView()
            generalRvAdapter.getWeatherData(data)
            binding.toolbarMainText.text = viewModel.city
        })


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() {
        generalRvAdapter = GeneralRvAdapter(requireContext())
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = generalRvAdapter
    }

}