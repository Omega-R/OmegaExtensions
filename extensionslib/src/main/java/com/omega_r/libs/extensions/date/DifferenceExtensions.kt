package com.omega_r.libs.extensions.date

import java.util.*
import java.util.concurrent.TimeUnit

fun Date.getDifferenceInDays(compareDate: Date): Long {
    val difference = compareDate.time - this.time
    return TimeUnit.MILLISECONDS.toDays(difference)
}

fun Date.getDifferenceInWeek(date: Date): Int {
    val calendar = Calendar.getInstance()
    val compareDate: Date = if (this.before(date)) {
        calendar.time = this
        date
    } else {
        calendar.time = date
        this
    }
    var i = 0
    while (calendar.time.before(compareDate)) {
        calendar.add(Calendar.WEEK_OF_YEAR, 1)
        i++
    }
    if (date.before(this)) {
        i *= -1
    }
    return --i
}

fun Date.getDifferenceInMonth(date: Date): Int {
    val calendar = Calendar.getInstance()
    val compareDate: Date = if (this.before(date)) {
        calendar.time = this
        date
    } else {
        calendar.time = date
        this
    }
    var i = 0
    while (calendar.time.before(compareDate)) {
        calendar.add(Calendar.MONTH, 1)
        i++
    }
    if (date.before(this)) {
        i *= -1
    }
    if (calendar.get(Calendar.DAY_OF_MONTH) == compareDate.getDateDayOfMonth()) {
        return i
    }
    return --i
}

fun Date.getMillisToNextMin(): Long {
    val calendar = Calendar.getInstance()
    calendar.time = this
    calendar.add(Calendar.MINUTE, 1)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)
    return calendar.timeInMillis - this.time
}