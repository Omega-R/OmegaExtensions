package omega_r.com.extensions.date

import android.annotation.SuppressLint
import android.text.format.DateUtils
import omega_r.com.extensions.date.DateExtensions.Companion.DEFAULT_FORMAT
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("ConstantLocale")
class DateExtensions {

    companion object {
        internal val HOURS_AND_MINS_FORMAT = SimpleDateFormat("HH:mm", Locale.getDefault())
        internal val DEFAULT_FORMAT = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

        @JvmStatic
        fun createDate(year: Int, month: Int, day: Int): Date {
            val calendar = Calendar.getInstance()
            calendar.set(year, month, day)
            return calendar.time
        }
    }

}

fun Date?.formatToHoursAndMinutes(): String? = this.formatDate(DateExtensions.HOURS_AND_MINS_FORMAT)

fun Date?.formatDate(formatter: DateFormat = DEFAULT_FORMAT): String? {
    this?.let {
        return formatter.format(this)
    }
    return null
}

fun Date?.isToday(): Boolean {
    if (this == null) return false
    return DateUtils.isToday(this.time)
}

fun Date?.isYesterday(): Boolean {
    if (this == null) return false

    val instance = Calendar.getInstance()
    instance.time = this
    val yesterday = Calendar.getInstance()
    yesterday.add(Calendar.DAY_OF_YEAR, -1)

    return (yesterday.get(Calendar.YEAR) == instance.get(Calendar.YEAR) &&
            yesterday.get(Calendar.DAY_OF_YEAR) == instance.get(Calendar.DAY_OF_YEAR))
}

fun Date?.isTomorrow(): Boolean {
    if (this == null) return false

    val instance = Calendar.getInstance()
    instance.time = this
    val tomorrow = Calendar.getInstance()
    tomorrow.add(Calendar.DAY_OF_YEAR, 1)

    return (tomorrow.get(Calendar.YEAR) == instance.get(Calendar.YEAR) &&
            tomorrow.get(Calendar.DAY_OF_YEAR) == instance.get(Calendar.DAY_OF_YEAR))
}

fun Date.getStartOfDay(): Date {
    val calendar = Calendar.getInstance()
    calendar.time = this
    calendar.set(Calendar.HOUR_OF_DAY, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)
    return calendar.time
}

fun Date?.getDateYear(): Int {
    val calendar = Calendar.getInstance()
    calendar.time = this ?: Date()
    return calendar.get(Calendar.YEAR)
}

fun Date?.getDateMonth(): Int {
    val calendar = Calendar.getInstance()
    calendar.time = this ?: Date()
    return calendar.get(Calendar.MONTH)
}

fun Date?.getDateDayOfMonth(): Int {
    val calendar = Calendar.getInstance()
    calendar.time = this ?: Date()
    return calendar.get(Calendar.DAY_OF_MONTH)
}