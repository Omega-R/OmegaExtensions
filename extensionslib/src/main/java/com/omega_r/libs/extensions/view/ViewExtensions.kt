package com.omega_r.libs.extensions.view

import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.view.ViewGroup
import androidx.annotation.*
import com.omega_r.libs.extensions.context.*

fun View.inflate(@LayoutRes resource: Int, root: ViewGroup): View {
    return View.inflate(context, resource, root)
}

@Suppress("NOTHING_TO_INLINE")
inline fun View.getCompatDrawable(@DrawableRes id: Int): Drawable? {
    return context.getCompatDrawable(id)
}

@Suppress("NOTHING_TO_INLINE")
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun View.getAnimatedVectorDrawable(@DrawableRes res: Int): AnimatedVectorDrawable? {
    return context.getAnimatedVectorDrawable(res)
}

@Suppress("NOTHING_TO_INLINE")
inline fun View.getCompatColor(@ColorRes id: Int): Int {
    return context.getCompatColor(id)
}

@Suppress("NOTHING_TO_INLINE")
inline fun View.getColorByAttribute(@AttrRes attrInt: Int): Int {
    return context.getColorByAttribute(attrInt)
}

@Suppress("NOTHING_TO_INLINE")
inline fun View.getColorDrawableByAttribute(@AttrRes attrInt: Int): ColorDrawable {
    return context.getColorDrawableByAttribute(attrInt)
}