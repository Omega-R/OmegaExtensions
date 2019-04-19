package omega_r.com.extensions.view

import android.view.Menu
import android.view.MenuItem

inline fun Menu.forEachIndexed(action: (index: Int, item: MenuItem) -> Unit) {
    for (index in 0 until size()) {
        action(index, getItem(index))
    }
}

fun Menu.indexOf(menuItem: MenuItem): Int {
    forEachIndexed { index, item ->
        if (menuItem.itemId == item.itemId) return index
    }
    return -1
}

fun Menu.getMenuItem(index: Int): MenuItem? {
    forEachIndexed { i, item ->
        if (index == i) return item
    }
    return null
}