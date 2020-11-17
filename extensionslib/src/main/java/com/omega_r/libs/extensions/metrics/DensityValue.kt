package com.omega_r.libs.extensions.metrics

import android.util.TypedValue.COMPLEX_UNIT_PX
import android.widget.TextView
import com.omega_r.libs.extensions.metrics.DensityUnit.Companion.convertMetricsUnit
import com.omega_r.libs.extensions.metrics.DensityUnit.DP
import com.omega_r.libs.extensions.metrics.DensityUnit.PX

/**
 * Created by Anton Knyazev on 16.11.2020.
 */
@Suppress("NON_PUBLIC_PRIMARY_CONSTRUCTOR_OF_INLINE_CLASS")
inline class DensityValue internal constructor(private val value: Float): Comparable<DensityValue> {

    companion object {

        private val storageUnit
            get() = PX

    }

    val inPx: Float
        get() = toFloat(PX)

    val inDp: Float
        get() = toFloat(DP)

    constructor(value: Float, unit: DensityUnit): this(convertMetricsUnit(value, unit, storageUnit))

    fun toFloat(unit: DensityUnit) = convertMetricsUnit(value, storageUnit, unit)

    fun toInt(unit: DensityUnit) = toFloat(unit).toInt()

    operator fun plus(metrics: DensityValue): DensityValue = DensityValue(value + metrics.value)

    operator fun minus(metrics: DensityValue): DensityValue = DensityValue(value - metrics.value)

    operator fun unaryMinus(): DensityValue = DensityValue(-value)

    operator fun times(scale: Int): DensityValue = DensityValue(value * scale)

    operator fun times(scale: Double): DensityValue = DensityValue((value * scale).toFloat())

    operator fun div(scale: Int): DensityValue = DensityValue(value / scale)

    operator fun div(scale: Double): DensityValue = DensityValue((value / scale).toFloat())

    operator fun div(other: DensityValue): Float = this.value / other.value

    override fun compareTo(other: DensityValue): Int {
        return value.compareTo(other.value)
    }

}

val Int.px
    get() = DensityValue(toFloat(), PX)

val Float.px
    get() = DensityValue(this, PX)

val Int.dp
    get() = DensityValue(toFloat(), DP)

val Float.dp
    get() = DensityValue(this, DP)

fun TextView.setTextSize(densityValue: DensityValue) = setTextSize(COMPLEX_UNIT_PX, densityValue.toFloat(PX))
