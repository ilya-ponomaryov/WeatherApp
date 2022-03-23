package com.example.weatherapp.common.utils

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

fun Fragment.showToast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
}

fun<T> Flow<T>.launchWhenCreated(lifecycleScope: LifecycleCoroutineScope) {
    lifecycleScope.launchWhenCreated {
        this@launchWhenCreated.collect()
    }
}