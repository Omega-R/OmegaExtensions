package com.omega_r.libs.extensions.list

fun <T> List<T>.contains(vararg values: T): Boolean {
    if (isEmpty()) return false

    var counter = 0
    values.forEach {
        if (contains(it)) counter++
    }
    return counter == values.size
}