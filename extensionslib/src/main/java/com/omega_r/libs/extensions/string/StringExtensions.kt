package com.omega_r.libs.extensions.string

import android.os.Build
import android.text.Html
import android.text.Spanned
import java.text.DateFormat
import java.text.ParseException
import java.util.*

fun String?.isInt(): Boolean {
    if (isNullOrBlank()) return false
    return try {
        val int = this!!.toInt()
        true
    } catch (exc: ParseException) {
        false
    }
}

fun String.removeSpace(): String = replace(" ", "")

fun String.toDate(format: DateFormat): Date? {
    return try {
        format.parse(this)
    } catch (exc: ParseException) {
        exc.printStackTrace()
        null
    }
}

fun String.fromHtml(): Spanned {
    return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
        @Suppress("deprecation")
        Html.fromHtml(this)
    } else {
        Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
    }
}