package omega_r.com.extensions.canvas

import android.graphics.Canvas
import android.graphics.Paint

fun Canvas.drawCircle(cx: Int, cy: Int, radius: Int, paint: Paint) {
    drawCircle(cx.toFloat(), cy.toFloat(), radius.toFloat(), paint)
}

fun Canvas.drawCircle(cx: Int, cy: Int, radius: Float, paint: Paint) {
    drawCircle(cx.toFloat(), cy.toFloat(), radius, paint)
}