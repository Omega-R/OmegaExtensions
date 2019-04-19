package omega_r.com.extensions.list

fun <T> List<T>.contains(vararg values: T): Boolean {
    if (isEmpty()) return false

    var counter = 0
    values.forEach {
        if (contains(it)) counter++
    }
    return counter == values.size
}