package com.example.weatherapp.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.example.weatherapp.R
import com.example.weatherapp.databinding.AddCityDialogLayoutBinding
import com.example.weatherapp.databinding.LocationFragmentBinding

class AddCityDialog : DialogFragment() {
    private var _binding: AddCityDialogLayoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AddCityDialogLayoutBinding.inflate(inflater, container, false)
        binding.cancelAddCityBtn.setOnClickListener {
            dismiss()
        }

        binding.addCityBtn.setOnClickListener {

        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog?.window?.setBackgroundDrawableResource(R.drawable.insert_dialog)
    }
}