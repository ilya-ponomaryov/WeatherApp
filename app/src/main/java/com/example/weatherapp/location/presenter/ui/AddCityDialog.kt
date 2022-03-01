package com.example.weatherapp.location.presenter.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.weatherapp.R
import com.example.weatherapp.common.SharingViewModel
import com.example.weatherapp.databinding.AddCityDialogLayoutBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlin.coroutines.coroutineContext

@AndroidEntryPoint
class AddCityDialog : DialogFragment() {
    private var _binding: AddCityDialogLayoutBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LocationViewModel by viewModels()
    private val sharingViewModel: SharingViewModel by viewModels()

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
            GlobalScope.launch(Dispatchers.Main) {
                viewModel.getLocationFromNetwork()
                onDismiss()
            }

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.locationLiveData.observe(viewLifecycleOwner, Observer {
            Log.d("AddCityDialog", it.toString())
            sharingViewModel.setShareLiveData(it)
        })
    }

    private suspend fun onDismiss() {
        GlobalScope.async {
            delay(500)
            dismiss()
        }.await()
    }


}