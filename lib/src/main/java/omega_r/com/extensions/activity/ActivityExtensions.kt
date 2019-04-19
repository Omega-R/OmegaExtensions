package omega_r.com.extensions.activity

import android.app.Activity
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.*
import omega_r.com.extensions.context.getCompatColor
import omega_r.com.extensions.context.getCompatDrawable

fun <T : View> Activity.bind(@IdRes res: Int): Lazy<T> {
    @Suppress("UNCHECKED_CAST")
    return lazy(LazyThreadSafetyMode.NONE) { findViewById<T>(res) }
}

fun Activity.bindColor(@ColorRes res: Int): Lazy<Int> {
    return lazy(LazyThreadSafetyMode.NONE) { this.getCompatColor(res) }
}

fun Activity.bindInt(@IntegerRes res: Int): Lazy<Int> {
    return lazy(LazyThreadSafetyMode.NONE) { this.resources.getInteger(res) }
}

fun Activity.bindString(@StringRes res: Int): Lazy<String> {
    return lazy(LazyThreadSafetyMode.NONE) { this.resources.getString(res) }
}

fun Activity.bindDrawable(@DrawableRes res: Int): Lazy<Drawable?> {
    return lazy(LazyThreadSafetyMode.NONE) { this.getCompatDrawable(res) }
}