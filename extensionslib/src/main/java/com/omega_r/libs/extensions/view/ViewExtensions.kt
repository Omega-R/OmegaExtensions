package com.omega_r.libs.extensions.view

import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.view.ViewCompat
import androidx.annotation.*
import com.omega_r.libs.extensions.context.*

fun View.inflate(@LayoutRes resource: Int, root: ViewGroup): View {
    return View.inflate(context, resource, root)
}

fun View.inflate(@LayoutRes resource: Int): View {
    return View.inflate(context, resource, null)
}

var View.paddingStartMutable: Int
    get() = ViewCompat.getPaddingStart(this)
    set(value) = setPaddingRelativeOverload(start = value)

var View.paddingEndMutable: Int
    get() = ViewCompat.getPaddingEnd(this)
    set(value) = setPaddingRelativeOverload(end = value)

var View.paddingTopMutable: Int
    get() = paddingTop
    set(value) = setPaddingOverload(top = value)

var View.paddingBottomMutable: Int
    get() = paddingBottom
    set(value) = setPaddingOverload(bottom = value)

var View.paddingMutable: Int
    get() = paddingLeft
    set(value) = setPaddingOverload(left = value)

var View.paddingRightMutable: Int
    get() = paddingRight
    set(value) = setPaddingOverload(right = value)

fun View.setPaddingOverload(
    left: Int = paddingLeft,
    top: Int = paddingTop,
    right: Int = paddingRight,
    bottom: Int = paddingBottom
) = setPadding(left, top, right, bottom)

fun View.setPaddingRelativeOverload(
    start: Int = ViewCompat.getPaddingStart(this),
    top: Int = paddingTop,
    end: Int = ViewCompat.getPaddingEnd(this),
    bottom: Int = paddingBottom
) = ViewCompat.setPaddingRelative(this, start, top, end, bottom)

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