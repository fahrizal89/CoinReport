package com.fahrizal.coinreport.ui

import android.Manifest
import android.content.Intent
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

        if (isMapPermitted()) {
            getLocation()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        getLocation()
    }

    private fun getLocation() {
        viewModel.getLocation()
    }

    private fun isMapPermitted(): Boolean {
        val permissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )

        return isAllowedToAccess(permissions, PERMISSION_MAP_CODE)
    }

    companion object {

        const val PERMISSION_MAP_CODE = 1000
    }
}