package com.omega_r.libs.extensions.view

import android.animation.LayoutTransition
import android.view.View
import android.view.ViewGroup

fun ViewGroup.getKids(): List<View> {
    val list = mutableListOf<View>()
    for (i in 0 until childCount) {
        list.add(getChildAt(i))
    }
    return list
}

fun ViewGroup.getFirstChildOrNull(): View? {
    if (childCount == 0) return null
    return getChildAt(0)
}

fun ViewGroup.getLastChildOrNull(): View? {
    if (childCount == 0) return null
    return getChildAt(childCount - 1)
}

var ViewGroup.animateLayoutChanges: Boolean
    get() = layoutTransition is LayoutTransition
    set(value) {
        layoutTransition = if (value) LayoutTransition() else null
    }
