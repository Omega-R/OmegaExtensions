package com.omega_r.libs.extensions.list

fun <T> Comparator<T>.sort(list: MutableList<T>) {
    list.sortWith(this)
}