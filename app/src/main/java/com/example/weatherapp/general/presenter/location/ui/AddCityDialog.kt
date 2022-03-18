package com.example.weatherapp.general.presenter.location.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.databinding.AddCityDialogLayoutBinding
import com.example.weatherapp.general.presenter.weather.ui.GeneralFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "Dialog"

@AndroidEntryPoint
class AddCityDialog : DialogFragment() {
    private var _binding: AddCityDialogLayoutBinding? = null
    private val binding get() = _binding!!

    private val navController: NavController get() = findNavController()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        AddCityDialogLayoutBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var city: String = ""

        binding.searchAddCityView.setIconifiedByDefault(true)
        binding.searchAddCityView.isFocusable = true
        binding.searchAddCityView.isIconified = false
        binding.searchAddCityView.requestFocusFromTouch()

        binding.cancelAddCityBtn.setOnClickListener {
            dismiss()
        }

        binding.searchAddCityView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                binding.searchAddCityView.clearFocus()
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query != null) {
                    Log.d("Search", query)
                    city = query
                }
                return false
            }
        })
        binding.addCityBtn.setOnClickListener {
            val action = GeneralFragmentDirections.actionGeneralFragmentSelf(city)
            navController.navigate(action)
            dismiss()
        }
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

    fun show(fragmentManager: FragmentManager) = show(fragmentManager, TAG)
}