package omega_r.com.extensions.common

fun Any?.ifNull(block: () -> Unit) {
    if (this == null) block()
}