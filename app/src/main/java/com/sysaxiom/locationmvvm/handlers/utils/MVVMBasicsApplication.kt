package com.sysaxiom.locationmvvm.handlers.utils

import android.app.Application
import com.sysaxiom.locationmvvm.handlers.location.LocationHandler
import com.sysaxiom.locationmvvm.handlers.ui.appointment.AppointmentViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MVVMBasicsApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@MVVMBasicsApplication))
        bind() from singleton { LocationHandler(instance()) }
        bind() from provider { AppointmentViewModelFactory(instance()) }
    }

}