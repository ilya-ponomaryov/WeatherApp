package com.example.weatherapp.location.presenter.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.R
import com.example.weatherapp.common.SharingViewModel
import com.example.weatherapp.common.utils.Constant
import com.example.weatherapp.databinding.AddCityDialogLayoutBinding
import com.example.weatherapp.location.data.models.LocationRequest
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class AddCityDialog : DialogFragment() {
    private var _binding: AddCityDialogLayoutBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LocationViewModel by viewModels()
    //private val sharingViewModel: SharingViewModel by viewModels()

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

        binding.searchAddCityView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                binding.searchAddCityView.clearFocus()
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query != null) {
                    val locationRequest = LocationRequest(query, 1, Constant.APPID)
                    viewModel.setLocationRequest(locationRequest)
                    viewModel.getLocationFromNetwork()
                    Log.d("Search", query)
                }

                return false
            }

        })
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
        val sharingViewModel = ViewModelProvider(requireActivity()).get(SharingViewModel::class.java)
        viewModel.locationLiveData.observe(viewLifecycleOwner, Observer {
            Log.d("AddCityDialog", it.toString())
            sharingViewModel.setShareLiveData(it)
        })
    }

    private suspend fun onDismiss() {
        withContext(Dispatchers.Default) {
            delay(500)
            dismiss()
        }
    }


}