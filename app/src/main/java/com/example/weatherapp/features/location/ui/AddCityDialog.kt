package com.example.weatherapp.features.location.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.example.weatherapp.R
import com.example.weatherapp.databinding.AddCityDialogLayoutBinding

class AddCityDialog : DialogFragment() {
    private var _binding: AddCityDialogLayoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AddCityDialogLayoutBinding.inflate(inflater, container, false)
        binding.searchAddCityView.setIconifiedByDefault(true)
        binding.searchAddCityView.isFocusable = true
        binding.searchAddCityView.isIconified = false
        binding.searchAddCityView.requestFocusFromTouch()
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
        dialog?.window?.setGravity(Gravity.BOTTOM)
        dialog?.window?.setBackgroundDrawableResource(R.drawable.insert_dialog)
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
    }


}