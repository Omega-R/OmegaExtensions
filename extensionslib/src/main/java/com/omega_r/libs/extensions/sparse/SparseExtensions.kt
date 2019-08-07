package com.omega_r.libs.extensions.sparse

import android.util.SparseArray

/**
 * Created by Anton Knyazev on 2019-08-07.
 */
inline fun <T> SparseArray<T>.getOrPut(key: Int, defaultValue: () -> T): T {
    val value = get(key)
    return if (value == null) {
        val answer = defaultValue()
        put(key, answer)
        answer
    } else {
        value
    }
}