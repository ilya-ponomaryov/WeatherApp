package com.example.weatherapp.features.location.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.weatherapp.databinding.LocationFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationFragment : Fragment() {
    private val viewModel: LocationViewModel by viewModels()
    private var _binding: LocationFragmentBinding? = null
    private val binding get() = _binding!!
    companion object {
        fun newInstance() = LocationFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LocationFragmentBinding.inflate(inflater, container, false)
        binding.addCityLocationBtn.setOnClickListener {
            val addCityDialog = AddCityDialog()
            fragmentManager?.let { it1 -> addCityDialog.show(it1, "Dialog") }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}