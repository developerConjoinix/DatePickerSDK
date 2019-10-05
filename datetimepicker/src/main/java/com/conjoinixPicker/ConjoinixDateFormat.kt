@file:Suppress("INTEGER_OVERFLOW")

package com.conjoinixPicker

import java.text.SimpleDateFormat
import java.util.*


private const val SECOND_MILLIS = 1000
private const val HALF_MINUTE_MILLIS = 30 * SECOND_MILLIS
private const val MINUTE_MILLIS = 60 * SECOND_MILLIS
private const val HOUR_MILLIS = 60 * MINUTE_MILLIS
private const val DAY_MILLIS = 24 * HOUR_MILLIS
private const val WEEKS_MILLIS = 7 * DAY_MILLIS
private const val MONTHS_MILLIS : Long = (30 * DAY_MILLIS.toLong())
private const val YEARS_MILLIS : Long =  365 * DAY_MILLIS.toLong()

const val Default = "yyyy-MM-dd HH:mm:ss"
const val hh_mm_a_DD_MMM_yyyy = "hh:mm a dd MMM yy"
const val hh_mm_ss_a_DD_MMM_yyyy = "hh:mm:ss a dd MMM yyyy"
const val ddd_dd = "hh:mm:ss a dd MMM yyyy"
const val yyyy_MM_DD = "yyyy-MM-dd"
const val DD_MMMM_yyyy = "dd MMMM yyyy"
const val EEEE_MMMM_yyyy = "EEEE MMMM yyyy"
const val DD_MMMM = "dd MMMM"
const val DD_MMM = "dd MMM"
const val hh_mm_a = "hh:mm a"
const val hh = "hh"
const val HH = "HH"
const val mm = "mm"
const val a = "a"
const val ss = "ss"
const val HH_mm_ss = "HH:mm:ss"
const val hh_mm_ss_a = "hh:mm:ss a"
const val DD = "dd"
const val MMM = "MMM"
const val MM = "MM"
const val yyyy = "yyyy"
const val yyyy_MMMM = "yyyy MMMM"

fun String.conjoinixDate(
    to: String = "hh:mm a DD MMM yy",
    from: String = Default
): String {

    return SimpleDateFormat(to, Locale.getDefault()).format(this.toDate(from))
}

fun String.toAmPmTime(
    from: String = HH_mm_ss,
    to: String = hh_mm_a
): String {

    return SimpleDateFormat(to, Locale.getDefault()).format(this.toDate(from))
}

fun String.toAmPmDate(
    from: String = Default,
    to: String = hh_mm_ss_a_DD_MMM_yyyy
): String {

    return SimpleDateFormat(to, Locale.getDefault()).format(this.toDate(from))
}


fun String.toDate(format: String = Default): Date {

    return SimpleDateFormat(format, Locale.getDefault()).parse(this)
}

fun Date.toStringDate(format: String = Default): String {

    return SimpleDateFormat(format, Locale.getDefault()).format(this)
}


fun nowDate(): String? {
    return Date().toStringDate(yyyy_MM_DD)
}


fun nowDateTime(): String {
    return Date().toStringDate(Default)
}


fun Date.dateDifferenceInDates(to: Date): Long {
    return (this.time - to.time)
}


fun String.dateDifferenceInString(to: String): Long {

    return this.toDate().dateDifferenceInDates(to.toDate())
}


fun String.timeAgo(format: String = Default): String {


    val endDate = nowDateTime().toDate()
    val startDate = this.toDate(format) // this function work with this format yyyy-MM-dd HH:mm:ss
    val different = endDate.time - startDate.time
    return when {
        different < HALF_MINUTE_MILLIS ->
            "Just Now"
        different < MINUTE_MILLIS ->
            "${different/SECOND_MILLIS} secs ago"
        different < 2 * MINUTE_MILLIS ->
            "1 mint ago"
        different < 50 * MINUTE_MILLIS ->
            "${different / MINUTE_MILLIS} mints ago"
        different < 90 * MINUTE_MILLIS ->
            "1 hour ago"
        different < 24 * HOUR_MILLIS ->
            "${different / HOUR_MILLIS} hours ago"
        different < 48 * HOUR_MILLIS ->
            "Yesterday"
        different < 7 * DAY_MILLIS ->
            "${different / DAY_MILLIS} days ago"
        different < 2 * WEEKS_MILLIS ->
            "${different / WEEKS_MILLIS} week ago"
        different < 3.5 * WEEKS_MILLIS -> {
            "${different / WEEKS_MILLIS} weeks ago"
        }
        different < 2 * MONTHS_MILLIS ->
            "${different / MONTHS_MILLIS} month ago"
        different < 11 * MONTHS_MILLIS ->
            "${different / MONTHS_MILLIS} months ago"
        different < 2 * YEARS_MILLIS ->
            "${different / YEARS_MILLIS} year ago"
        different < 12 * YEARS_MILLIS ->
            "${different / YEARS_MILLIS} years ago"
        else ->  startDate.toStringDate()
    }
}

fun Long.convertToReadable(isWithUnits: Boolean = true): String? {
    val seconds = this / 1000
    val minutes = seconds / 60
    val hours = minutes / 60
    val days = hours / 24
    if (isWithUnits) {
        return when {
            days > 0        -> "$days days : ${hours % 24} hours"
            hours > 0 -> "${hours % 24} hours : ${(minutes % 60).correct()} mints"
            minutes > 0 -> "${(minutes % 60).correct()} mints : ${(seconds % 60).correct()} secs"
            else -> "${(seconds % 60).correct()} secs"
        }
    }
    return when {
        days > 0 -> "$days:${hours % 24}"
        hours > 0 -> "${hours % 24}:${(minutes % 60).correct()}"
        minutes > 0 -> "${(minutes % 60).correct()}:${(seconds % 60).correct()}"
        else -> (seconds % 60).correct()
    }


}

fun Long.correct(): String {

    return if (this > 9) {
        "$this"
    } else {
        "0$this"
    }
}