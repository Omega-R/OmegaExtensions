package com.omega_r.libs.extensions.view

import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun View.inflate(@LayoutRes resource: Int, root: ViewGroup): View {
    return View.inflate(context, resource, root)
}