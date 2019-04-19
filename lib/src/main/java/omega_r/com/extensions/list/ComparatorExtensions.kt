package omega_r.com.extensions.list

fun <T> Comparator<T>.sort(list: MutableList<T>) {
    list.sortWith(this)
}