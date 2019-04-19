package omega_r.com.extensions.fragment

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

inline fun FragmentManager.edit(block: FragmentTransaction.() -> Unit) {
    beginTransaction()
        .apply(block)
        .commit()
}