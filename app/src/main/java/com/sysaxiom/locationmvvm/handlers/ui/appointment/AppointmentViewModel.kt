package com.sysaxiom.locationmvvm.handlers.ui.appointment

import android.content.Context
import androidx.lifecycle.ViewModel
import com.sysaxiom.locationmvvm.handlers.location.LocationHandler

class AppointmentViewModel(
    private val context: Context
) : ViewModel() {

    fun getLocation() = LocationHandler(context)

}