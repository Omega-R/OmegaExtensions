package com.omega_r.libs.extensions.context

import android.content.Context
import android.provider.Settings

val Context.orientation: Int
    get() {
        return resources.configuration.orientation
    }

val Context.isAutoRotateEnabled: Boolean
    get() {
        return try {
            Settings.System.getInt(contentResolver, Settings.System.ACCELEROMETER_ROTATION, 0) == 1
        } catch (exc: SecurityException) {
            exc.printStackTrace()
            true
        }
    }