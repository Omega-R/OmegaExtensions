package com.omega_r.libs.extensions.common

fun Any?.ifNull(block: () -> Unit) {
    if (this == null) block()
}