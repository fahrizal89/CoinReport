package com.fahrizal.coinreport.util

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.os.StrictMode
import androidx.core.app.ActivityCompat


object PermissionUtil {

    fun Activity.isAllowedToAccess(permissionType: String, requestCode: Int): Boolean {
        return isAllowedToAccess(arrayOf(permissionType), requestCode)
    }

    fun Activity.isAllowedToAccess(permissionTypes: Array<String>, requestCode: Int): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            permissionTypes.forEach { permissionType ->
                if (checkSelfPermission(permissionType) != PackageManager.PERMISSION_GRANTED) {
                    requestPermission(this, permissionTypes, requestCode)
                    return false
                }
            }
        }
        return true
    }

    fun ignoreFileUriExposed() = lowStrictMode

    private fun requestPermission(
        activity: Activity,
        permissionTypes: Array<String>,
        requestCode: Int
    ) {
        ActivityCompat.requestPermissions(activity, permissionTypes, requestCode)
    }

    private val lowStrictMode by lazy {
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
    }
}