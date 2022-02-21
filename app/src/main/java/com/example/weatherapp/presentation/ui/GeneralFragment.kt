package com.example.weatherapp.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.weatherapp.databinding.GeneralFragmentBinding


@AndroidEntryPoint
class GeneralFragment : Fragment() {
    private val viewModel: GeneralViewModel by viewModels()
    private var _binding: GeneralFragmentBinding? = null
    private val binding get() = _binding!!

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
        viewModel.weatherLiveData.observe(viewLifecycleOwner, Observer {
            Log.d("GeneralFragmentLog", it.current.temp.toInt().toString())
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}