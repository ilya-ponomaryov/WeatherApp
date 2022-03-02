package com.example.weatherapp.general.presenter.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.databinding.GeneralFragmentBinding
import com.example.weatherapp.general.presenter.adapters.GeneralRvAdapter
import com.example.weatherapp.location.presenter.ui.AddCityDialog
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class GeneralFragment : Fragment() {
    private lateinit var viewModel: GeneralViewModel
    private var _binding: GeneralFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var generalRvAdapter: GeneralRvAdapter
    private lateinit var navController: NavController

    companion object {
        fun newInstance() = GeneralFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        navController = findNavController()
        _binding = GeneralFragmentBinding.inflate(inflater, container, false)
        recyclerView = binding.generalRv
        binding.searchToolbarBtn.setOnClickListener {
            val addCityDialog = AddCityDialog()
            fragmentManager?.let { it1 -> addCityDialog.show(it1, "Dialog") }

        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(GeneralViewModel::class.java)
        viewModel.weatherLiveData.observe(viewLifecycleOwner, Observer {
            Log.d("GeneralFragmentLog", it.current.temp.toInt().toString())
            setupRecyclerView()
            generalRvAdapter.getWeatherData(it)
        })
        binding.toolbarMainText.text = viewModel.city

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