package com.omega_r.libs.extensions.view

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


fun RecyclerView.isReverseLayout(): Boolean {
    return this.layoutManager?.isReverseLayout() ?: false
}

fun RecyclerView.LayoutManager.isReverseLayout(): Boolean {
    if (this is LinearLayoutManager) {
        return this.reverseLayout
    }
    return false
}

fun RecyclerView.getFirstVisibleItemPosition(): Int? {
    return layoutManager?.let { manager ->
        return when (manager) {
            is LinearLayoutManager -> manager.findFirstVisibleItemPosition()
            is GridLayoutManager -> manager.findFirstVisibleItemPosition()
            else -> null
        }
    }
}

fun RecyclerView.getLastVisibleItemPosition(): Int? {
    return layoutManager?.let { manager ->
        return when (manager) {
            is LinearLayoutManager -> manager.findLastVisibleItemPosition()
            is GridLayoutManager -> manager.findLastVisibleItemPosition()
            else -> null
        }
    }
}

fun RecyclerView.addOnScrollListener(onScrollStateChanged: ((state: Int) -> Unit)? = null,
                                     onScrolled: ((dx: Int, dy: Int) -> Unit)? = null) {

    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            onScrolled?.invoke(dx, dy)
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            onScrollStateChanged?.invoke(newState)
        }
    })

}

fun RecyclerView.addOnScrollPositionListener(onChanged: ((firstVisiblePos: Int, lastVisiblePos: Int) -> Unit)? = null) {

    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            onChanged?.let {
                val startPosition = recyclerView.getFirstVisibleItemPosition()
                val endPosition = recyclerView.getLastVisibleItemPosition()
                if (startPosition != null && endPosition != null) {
                    it.invoke(startPosition, endPosition)
                }
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            // nothing
        }
    })

}