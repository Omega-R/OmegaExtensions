package omega_r.com.extensions.date

import java.util.*

fun Date.getAge(): Int {
    val birthday = Calendar.getInstance()
    birthday.time = this
    val today = Calendar.getInstance()

    var age = today.get(Calendar.YEAR) - birthday.get(Calendar.YEAR)
    if (today.get(Calendar.DAY_OF_YEAR) < birthday.get(Calendar.DAY_OF_YEAR)) {
        age -= 1
    }
    return age
}
