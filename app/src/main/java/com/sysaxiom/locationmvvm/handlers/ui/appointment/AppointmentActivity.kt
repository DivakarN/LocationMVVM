package com.sysaxiom.locationmvvm.handlers.ui.appointment

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sysaxiom.locationmvvm.R
import com.sysaxiom.locationmvvm.handlers.utils.UrlsFields.NO_LOCATION_DATA
import kotlinx.android.synthetic.main.activity_appointment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

const val REQUEST_LOCATION_PERMISSION = 0

class AppointmentActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()

    private val factory : AppointmentViewModelFactory by instance()

    lateinit var viewModel: AppointmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointment)

        viewModel = ViewModelProviders.of(this, factory).get(AppointmentViewModel::class.java)

        if (ActivityCompat.checkSelfPermission(this@AppointmentActivity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this@AppointmentActivity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_LOCATION_PERMISSION)
        }

        viewModel.getLocation().observe(this, Observer {
            if(it.isPermissionAvailable.equals(true)){
                textview.text = it.toString()
            } else if(it.isPermissionAvailable.equals(false)){
                textview.text = NO_LOCATION_DATA
            }
        })
    }
}
