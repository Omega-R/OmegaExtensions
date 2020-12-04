package com.omega_r.libs.extensions.fragment

import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.annotation.AttrRes
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.omega_r.libs.extensions.context.*

/**
 * Created by Anton Knyazev on 04.12.2020.
 */
@Suppress("NOTHING_TO_INLINE")
inline fun Fragment.getCompatDrawable(@DrawableRes id: Int): Drawable? {
    return requireContext().getCompatDrawable(id)
}

@Suppress("NOTHING_TO_INLINE")
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun Fragment.getAnimatedVectorDrawable(@DrawableRes res: Int): AnimatedVectorDrawable? {
    return requireContext().getAnimatedVectorDrawable(res)
}

@Suppress("NOTHING_TO_INLINE")
inline fun Fragment.getCompatColor(@ColorRes id: Int): Int {
    return requireContext().getCompatColor(id)
}

@Suppress("NOTHING_TO_INLINE")
inline fun Fragment.getColorByAttribute(@AttrRes attrInt: Int): Int {
    return requireContext().getColorByAttribute(attrInt)
}

@Suppress("NOTHING_TO_INLINE")
inline fun Fragment.getColorDrawableByAttribute(@AttrRes attrInt: Int): ColorDrawable {
    return requireContext().getColorDrawableByAttribute(attrInt)
}