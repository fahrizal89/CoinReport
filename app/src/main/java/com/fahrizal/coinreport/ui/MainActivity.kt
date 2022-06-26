package com.fahrizal.coinreport.ui

import android.Manifest.permission.*
import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.fahrizal.coinreport.R
import com.fahrizal.coinreport.util.PermissionUtil.isAllowedToAccess
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkLocationPermissions()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        checkLocationPermissions()
    }

    private fun checkLocationPermissions() {
        val permissions = arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION)

        if (isAllowedToAccess(permissions, PERMISSION_MAP_CODE)) {
            isBackgroundLocationPermitted()
        }
    }

    private fun isBackgroundLocationPermitted(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            isAllowedToAccess(ACCESS_BACKGROUND_LOCATION, PERMISSION_BACKGROUND_MAP_CODE)
        } else {
            return true
        }
    }

    companion object {

        private const val PERMISSION_MAP_CODE = 1000
        private const val PERMISSION_BACKGROUND_MAP_CODE = 1003
    }
}