package com.omega_r.libs.extensions.metrics

import android.content.res.Resources

fun Int.toDp(): Int = toFloat().toDp().toInt()

fun Int.toPx(): Int = toFloat().toPx().toInt()

fun Float.toDp(): Float = (this / Resources.getSystem().displayMetrics.density)

fun Float.toPx(): Float = (this * Resources.getSystem().displayMetrics.density)