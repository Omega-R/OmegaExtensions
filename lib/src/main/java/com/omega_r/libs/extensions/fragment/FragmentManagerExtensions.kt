package com.omega_r.libs.extensions.fragment

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

inline fun FragmentManager.edit(block: FragmentTransaction.() -> Unit) {
    beginTransaction()
        .apply(block)
        .commit()
}