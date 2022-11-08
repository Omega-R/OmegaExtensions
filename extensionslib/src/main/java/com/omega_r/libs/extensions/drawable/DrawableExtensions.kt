package com.omega_r.libs.extensions.drawable

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.ScaleDrawable
import android.view.Gravity.NO_GRAVITY
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.omega_r.libs.extensions.context.getCompatDrawable

fun Context.getCompatDrawable(@DrawableRes id: Int, @ColorRes color: Int): Drawable? =
    getCompatDrawable(id)?.tint(this, color)

fun Drawable.tint(context: Context, @ColorRes color: Int): Drawable =
    tint(ContextCompat.getColor(context, color))

fun Drawable.tint(@ColorInt color: Int): Drawable {
    return DrawableCompat.wrap(this).mutate().apply {
        DrawableCompat.setTint(this, color)
    }
}

fun Drawable.scale(size: Int): Drawable = scale(size, size)

fun Drawable.scale(height: Int, width: Int): Drawable {
    return ScaleDrawable(this, NO_GRAVITY, width.toFloat(), height.toFloat())
}