package com.omega_r.libs.extensions.view

import android.view.View

var View.scale: Float
    get() = scaleX
    set(value) {
        scaleX = value
        scaleY = value
    }