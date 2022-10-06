package com.omega_r.libs.extensions.context

import android.content.Context
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat

@Suppress("NOTHING_TO_INLINE")
inline fun Context.getCompatDrawable(@DrawableRes id: Int): Drawable? {
    return ContextCompat.getDrawable(this, id)
}

@Suppress("NOTHING_TO_INLINE")
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun Context.getAnimatedVectorDrawable(@DrawableRes res: Int): AnimatedVectorDrawable? {
    return getCompatDrawable(res) as? AnimatedVectorDrawable
}

@Suppress("NOTHING_TO_INLINE")
inline fun Context.getCompatColor(@ColorRes id: Int): Int {
    return ContextCompat.getColor(this, id)
}

@Suppress("NOTHING_TO_INLINE")
inline fun Context.getColorByAttribute(@AttrRes attrInt: Int): Int {
    return TypedValue().run {
        if (theme.resolveAttribute(attrInt, this, true)) data else 0
    }
}

@Suppress("NOTHING_TO_INLINE")
inline fun Context.getColorDrawableByAttribute(@AttrRes attrInt: Int): ColorDrawable {
    return ColorDrawable(getColorByAttribute(attrInt))
}

@Suppress("NOTHING_TO_INLINE")
inline fun Context.getDrawableByAttribute(@AttrRes attrInt: Int): Drawable? {
    return TypedValue().run {
        if (theme.resolveAttribute(attrInt, this, true)) getCompatDrawable(resourceId) else null
    }
}