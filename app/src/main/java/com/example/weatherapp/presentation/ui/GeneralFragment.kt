package com.example.weatherapp.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.databinding.GeneralFragmentBinding
import com.example.weatherapp.presentation.adapter.GeneralRvAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class GeneralFragment : Fragment() {
    private val viewModel: GeneralViewModel by viewModels()
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
        binding.locationToolbarBtn.setOnClickListener {
            navController.navigate(R.id.action_generalFragment_to_locationFragment)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharingViewModel = ViewModelProvider(requireActivity()).get(SharingViewModel::class.java)
        viewModel.weatherLiveData.observe(viewLifecycleOwner, Observer {
            Log.d("GeneralFragmentLog", it.current.temp.toInt().toString())
            setupRecyclerView()
            generalRvAdapter.getWeatherData(it)
            sharingViewModel.setShareLiveData(it)
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