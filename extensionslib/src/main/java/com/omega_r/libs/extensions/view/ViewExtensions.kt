package com.omega_r.libs.extensions.view

import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.view.ViewCompat

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