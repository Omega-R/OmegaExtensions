package com.omega_r.libs.extensions.view

import android.graphics.drawable.Drawable
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.text.*
import android.text.Annotation
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.UnderlineSpan
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.omega_r.libs.extensions.context.getCompatColor

fun TextView.addTextListener(
    beforeChanged: ((s: CharSequence) -> Unit)? = null,
    onChanged: ((s: CharSequence) -> Unit)? = null,
    afterChanged: ((s: Editable) -> Unit)? = null
) {
    addDebounceTextListener(
        debounceTimeInMillis = 0,
        beforeChanged = beforeChanged, onChanged = onChanged, afterChanged = afterChanged
    )
}

fun TextView.addDebounceTextListener(
    debounceTimeInMillis: Long = 500,
    beforeChanged: ((s: CharSequence) -> Unit)? = null,
    afterChanged: ((s: Editable) -> Unit)? = null,
    onChanged: ((s: CharSequence) -> Unit)? = null
) {
    addTextChangedListener(object : TextWatcher {
        private val changedRunnable: Runnable = Runnable {
            onChanged?.invoke(text)
        }

        override fun afterTextChanged(s: Editable) {
            afterChanged?.invoke(s)
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            beforeChanged?.invoke(s)
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            if (onChanged == null) return

            if (debounceTimeInMillis <= 0) {
                onChanged.invoke(s)
                return
            }

            removeCallbacks(changedRunnable)
            postDelayed(changedRunnable, debounceTimeInMillis)
        }
    })
}

fun TextView.addDebounceChangeStateListener(
    delayInMillis: Long = 500,
    timeoutInMillis: Long = 0,
    listener: (Boolean) -> Unit
) {
    addTextChangedListener(object : TextWatcher {
        private var start: Boolean = false
        private val runnable: Runnable = Runnable {
            start = false
            listener(false)
            removeCallbacks(timeoutRunnable)

        }

        private val timeoutRunnable: Runnable = object : Runnable {
            override fun run() {
                listener(true)
                postDelayed(this, timeoutInMillis)
            }
        }

        override fun afterTextChanged(s: Editable) {
            //nothing
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            //nothing
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            if (!this.start) {
                this.start = true
                listener(true)
                postDelayed(timeoutRunnable, timeoutInMillis)
            } else {
                if (s.isEmpty()) {
                    listener(false)
                    this.start = false
                    removeCallbacks(timeoutRunnable)
                }
            }
            removeCallbacks(runnable)
            postDelayed(runnable, delayInMillis)
        }
    })
}

fun TextView.addLinkCallback(needUnderline: Boolean = true, callback: LinkCallback) {
    val text = text as SpannedString

    val spannableString = SpannableString(text)

    val annotations = text.getSpans(
        0, text.length,
        Annotation::class.java
    )
    for (annotation in annotations) {
        if (annotation.key == "link") {
            val start = text.getSpanStart(annotation)
            val end = text.getSpanEnd(annotation)

            spannableString.setSpan(object : ClickableSpan() {
                override fun onClick(widget: View) {
                    widget.post { widget.invalidate() } // Fix bug with background
                    callback.onLinkClick(annotation.value)
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.isUnderlineText = false
                }

            }, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            if (needUnderline) {
                spannableString.setSpan(UnderlineSpan(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
        }
    }

    this.text = spannableString

    if (movementMethod == null) {
        movementMethod = LinkMovementMethod.getInstance();
    }

}

fun TextView.setTextColorResource(@ColorRes colorRes: Int) {
    setTextColor(getCompatColor(colorRes))
}

private fun TextView.getDrawableRelative(index: Int): Drawable? {
    val drawables =
        if (VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN_MR1) compoundDrawablesRelative else compoundDrawables
    return drawables[index]
}

private fun TextView.setDrawableRelative(index: Int, drawable: Drawable?) {
    val drawables =
        if (VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN_MR1) compoundDrawablesRelative else compoundDrawables

    drawables[index] = drawable

    if (VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN_MR1) {
        setCompoundDrawablesRelativeWithIntrinsicBounds(drawables[0], drawables[1], drawables[2], drawables[3])
    } else {
        setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], drawables[2], drawables[3])
    }
}

private fun TextView.getDrawable(index: Int): Drawable? {
    val drawables = compoundDrawables
    return drawables[index]
}

private fun TextView.setDrawable(index: Int, drawable: Drawable?) {
    val drawables = compoundDrawables

    drawables[index] = drawable

    setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], drawables[2], drawables[3])
}

var TextView.drawableLeft: Drawable?
    get() = getDrawable(0)
    set(value) = setDrawable(0, value)

fun TextView.setDrawableLeft(drawableRes: Int) {
    drawableLeft = getCompatDrawable(drawableRes)
}

var TextView.drawableStart: Drawable?
    get() = getDrawableRelative(0)
    set(value) = setDrawableRelative(0, value)

fun TextView.setDrawableStart(drawableRes: Int) {
    drawableStart = getCompatDrawable(drawableRes)
}

var TextView.drawableRight: Drawable?
    get() = getDrawable(2)
    set(value) = setDrawableRelative(2, value)

fun TextView.setDrawableRight(drawableRes: Int) {
    drawableRight = getCompatDrawable(drawableRes)
}

var TextView.drawableEnd: Drawable?
    get() = getDrawableRelative(2)
    set(value) = setDrawableRelative(2, value)

fun TextView.setDrawableEnd(drawableRes: Int) {
    drawableEnd = getCompatDrawable(drawableRes)
}

var TextView.drawableTop: Drawable?
    get() = getDrawableRelative(1)
    set(value) = setDrawableRelative(1, value)

fun TextView.setDrawableTop(drawableRes: Int) {
    drawableTop = getCompatDrawable(drawableRes)
}

var TextView.drawableBottom: Drawable?
    get() = getDrawableRelative(3)
    set(value) = setDrawableRelative(3, value)

fun TextView.setDrawableBottom(drawableRes: Int) {
    drawableBottom = getCompatDrawable(drawableRes)
}

fun TextView.removeCompoundDrawables() {
    setCompoundDrawables(null, null, null, null)
    if (VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN_MR1) setCompoundDrawablesRelative(null, null, null, null)
}

interface LinkCallback {

    fun onLinkClick(link: String)

}