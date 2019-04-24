package com.omega_r.libs.extensions.log

import android.util.Log

inline fun <reified T> T.log(message: String) =
    Log.d(T::class.java.simpleName, message)

inline fun <reified T> T.log(exc: Throwable) {
    exc.printStackTrace()
    Log.e(T::class.java.simpleName, exc.message ?: "no-message")
}