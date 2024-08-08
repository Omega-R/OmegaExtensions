package com.omega_r.libs.extensions.fragment

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

inline fun FragmentManager.edit(
    commitNow: Boolean = false,
    allowingStateLoss: Boolean = false,
    block: FragmentTransaction.() -> Unit
) {
    beginTransaction()
        .apply(block)
        .apply {
            if (commitNow) {
                if (allowingStateLoss) {
                    commitNowAllowingStateLoss()
                } else {
                    commitNow()
                }
            } else {
                if (allowingStateLoss) {
                    commitAllowingStateLoss()
                } else {
                    commit()
                }
            }
        }
}