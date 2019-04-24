package com.omega_r.libs.extensions.view

import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.*
import com.omega_r.libs.extensions.context.getCompatColor
import com.omega_r.libs.extensions.context.getCompatDrawable

fun <T : View> View.bind(@IdRes res: Int): Lazy<T> {
    @Suppress("UNCHECKED_CAST")
    return lazy(LazyThreadSafetyMode.NONE) { findViewById<T>(res) }
}

fun View.bindColor(@ColorRes res: Int): Lazy<Int> {
    return lazy(LazyThreadSafetyMode.NONE) { context.getCompatColor(res) }
}

fun View.bindInt(@IntegerRes res: Int): Lazy<Int> {
    return lazy(LazyThreadSafetyMode.NONE) { this.resources.getInteger(res) }
}

fun View.bindString(@StringRes res: Int): Lazy<String> {
    return lazy(LazyThreadSafetyMode.NONE) { this.resources.getString(res) }
}

fun View.bindDrawable(@DrawableRes res: Int): Lazy<Drawable?> {
    return lazy(LazyThreadSafetyMode.NONE) { context.getCompatDrawable(res) }
}