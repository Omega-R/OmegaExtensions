package com.omega_r.libs.extensions.context

import android.content.Context
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat

fun Context.getCompatDrawable(@DrawableRes id: Int): Drawable? {
    return ContextCompat.getDrawable(this, id)
}

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Context.getAnimatedVectorDrawable(@DrawableRes res: Int): AnimatedVectorDrawable? {
    val drawable = getCompatDrawable(res)
    if (drawable == null || drawable !is AnimatedVectorDrawable) return null
    return drawable
}

fun Context.getCompatColor(@ColorRes id: Int): Int {
    return ContextCompat.getColor(this, id)
}

fun Context.getColorByAttribute(@AttrRes attrInt: Int): Int {
    return TypedValue().run {
        if (theme.resolveAttribute(attrInt, this, true)) data else 0
    }
}