package com.omega_r.libs.extensions.view

import android.graphics.drawable.Drawable
import android.os.Build.*
import android.text.*
import android.text.Annotation
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.UnderlineSpan
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorRes
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

fun TextView.addDebounceChangeStateListener(delayInMillis: Long = 500, timeoutInMillis: Long = 0, listener: (Boolean) -> Unit) {
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
                override fun onClick(widget: View?) {
                    widget?.post { widget.invalidate() } // Fix bug with background
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
    setTextColor(context.getCompatColor(colorRes))
}

var TextView.drawableLeft: Drawable?
    get() = compoundDrawables[0]
    set(value) {
        val drawables = compoundDrawables
        setCompoundDrawablesWithIntrinsicBounds(value, drawables[1], drawables[2], drawables[3])
    }

var TextView.drawableStart: Drawable?
    get() = (if (VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN_MR1) compoundDrawablesRelative[0] else compoundDrawables[0])
    set(value) {
        val drawables = if (VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN_MR1) {
            compoundDrawablesRelative
        } else {
            compoundDrawables
        }
        if (VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN_MR1) {
            setCompoundDrawablesRelativeWithIntrinsicBounds(value, drawables[1], drawables[2], drawables[3])
        } else {
            setCompoundDrawablesWithIntrinsicBounds(value, drawables[1], drawables[2], drawables[3])
        }
    }

var TextView.drawableRight: Drawable?
    get() = compoundDrawables[2]
    set(value) {
        val drawables = compoundDrawables
        setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], value, drawables[3])
    }

var TextView.drawableEnd: Drawable?
    get() = (if (VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN_MR1) compoundDrawablesRelative[2] else compoundDrawables[2])
    set(value) {
        val drawables = if (VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN_MR1) {
            compoundDrawablesRelative
        } else {
            compoundDrawables
        }

        if (VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN_MR1) {
            setCompoundDrawablesRelativeWithIntrinsicBounds(drawables[0], drawables[1], value, drawables[3])
        } else {
            setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], value, drawables[3])
        }
    }

var TextView.drawableTop: Drawable?
    get() = (if (VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN_MR1) compoundDrawablesRelative[1] else compoundDrawables[1])
    set(value) {
        val drawables = if (VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN_MR1) {
            compoundDrawablesRelative
        } else {
            compoundDrawables
        }
        if (VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN_MR1) {
            setCompoundDrawablesRelativeWithIntrinsicBounds(drawables[0], value, drawables[2], drawables[3])
        } else {
            setCompoundDrawablesWithIntrinsicBounds(drawables[0], value, drawables[2], drawables[3])
        }
    }

var TextView.drawableBottom: Drawable?
    get() = (if (VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN_MR1) compoundDrawablesRelative[3] else compoundDrawables[3])
    set(value) {
        val drawables = if (VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN_MR1) {
            compoundDrawablesRelative
        } else {
            compoundDrawables
        }
        if (VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN_MR1) {
            setCompoundDrawablesRelativeWithIntrinsicBounds(drawables[0], drawables[1], drawables[2], value)
        } else {
            setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], drawables[2], value)
        }
    }

fun TextView.removeCompoundDrawables() {
    setCompoundDrawables(null, null, null, null)
    if (VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN_MR1) setCompoundDrawablesRelative(null, null, null, null)
}

interface LinkCallback {

    fun onLinkClick(link: String)

}