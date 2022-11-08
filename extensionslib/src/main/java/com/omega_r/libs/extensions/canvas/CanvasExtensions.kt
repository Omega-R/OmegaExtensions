package com.omega_r.libs.extensions.canvas

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable

fun Canvas.drawCircle(cx: Int, cy: Int, radius: Int, paint: Paint) {
    drawCircle(cx.toFloat(), cy.toFloat(), radius.toFloat(), paint)
}

fun Canvas.drawCircle(cx: Int, cy: Int, radius: Float, paint: Paint) {
    drawCircle(cx.toFloat(), cy.toFloat(), radius, paint)
}

fun Canvas.drawDrawable(left: Float, top: Float, right: Float, bottom: Float, drawable: Drawable) {
    drawable.setBounds(left.toInt(), top.toInt(), right.toInt(), bottom.toInt())
    drawable.draw(this)
}

fun Canvas.drawDrawable(left: Int, top: Int, right: Int, bottom: Int, drawable: Drawable) {
    drawable.setBounds(left, top, right, bottom)
    drawable.draw(this)
}

fun Canvas.drawDrawable(rect: Rect, drawable: Drawable) {
    drawable.bounds = rect
    drawable.draw(this)
}
