package com.omega_r.libs.extensions.metrics

import android.content.res.Resources

/**
 * Created by Anton Knyazev on 16.11.2020.
 */
enum class DensityUnit (private val coefToPx: Float){
    PX(1f),
    DP(Resources.getSystem().displayMetrics.density),
    SP(Resources.getSystem().displayMetrics.scaledDensity);

    companion object {

        fun convertMetricsUnit(value: Float, sourceUnit: DensityUnit, targetUnit: DensityUnit): Float {
            return when {
                sourceUnit == targetUnit -> value
                targetUnit == PX -> value * targetUnit.coefToPx
                else -> value * (targetUnit.coefToPx / sourceUnit.coefToPx)
            }
        }

    }

}