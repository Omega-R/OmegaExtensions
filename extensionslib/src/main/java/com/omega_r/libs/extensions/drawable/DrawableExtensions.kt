package com.omega_r.libs.extensions.drawable

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
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

fun Drawable?.tint(context: Context, @ColorRes color: Int): Drawable? =
    this?.tint(ContextCompat.getColor(context, color))

fun Drawable?.tint(@ColorInt color: Int): Drawable? {
    return this?.let {
        val mutate = DrawableCompat.wrap(it).mutate()
        DrawableCompat.setTint(mutate, color)
        return@let mutate
    }
}

fun Drawable?.scale(resources: Resources, size: Int) : Drawable? = this?.scale(resources, size, size)

fun Drawable?.scale(resources: Resources, height: Int, width: Int) : Drawable? {
    if (this == null) return null
    if (this is BitmapDrawable) {
        bitmap?.let {
            val bitmap: Bitmap? = Bitmap.createScaledBitmap(it, height, width, true)
            if (bitmap != null) return BitmapDrawable(resources, bitmap)
        }
    }

    val scaleDrawable = ScaleDrawable(this, NO_GRAVITY, width.toFloat(), height.toFloat()).drawable
    scaleDrawable?.let {
        it.setBounds(0, 0, width, height)
        return it
    }

    return this
}