package com.omega_r.libs.extensions.context

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper

private fun Context.asActivity(): Activity? {
    return when(this) {
        is Activity -> this
        is ContextWrapper -> baseContext.asActivity()
        else -> null
    }
}