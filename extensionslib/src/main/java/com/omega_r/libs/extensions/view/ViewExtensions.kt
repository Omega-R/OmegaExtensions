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

fun View.getCompatDrawable(@DrawableRes id: Int): Drawable? {
    return context.getCompatDrawable(id)
}

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun View.getAnimatedVectorDrawable(@DrawableRes res: Int): AnimatedVectorDrawable? {
    return context.getAnimatedVectorDrawable(res)
}

fun View.getCompatColor(@ColorRes id: Int): Int {
    return context.getCompatColor(id)
}

fun View.getColorByAttribute(@AttrRes attrInt: Int): Int {
    return context.getColorByAttribute(attrInt)
}

fun View.getColorDrawableByAttribute(@AttrRes attrInt: Int): ColorDrawable {
    return context.getColorDrawableByAttribute(attrInt)
}