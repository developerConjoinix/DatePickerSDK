package com.conjoinixPicker

import java.text.SimpleDateFormat
import java.util.*


private const val SECOND_MILLIS   = 1000
private const val MINUTE_MILLIS   = 60 * SECOND_MILLIS
private const val HOUR_MILLIS     = 60 * MINUTE_MILLIS
private const val DAY_MILLIS      = 24 * HOUR_MILLIS
private const val WEEKS_MILLIS    = 7  * DAY_MILLIS
val Default                = "yyyy-MM-dd HH:mm:ss"
val hh_mm_a_DD_MMM_yyyy    = "hh:mm a dd MMM yy"
val hh_mm_ss_a_DD_MMM_yyyy = "hh:mm:ss a dd MMM yyyy"
val ddd_dd         = "hh:mm:ss a dd MMM yyyy"
val yyyy_MM_DD     = "yyyy-MM-dd"
val DD_MMMM_yyyy   = "dd MMMM yyyy"
val EEEE_MMMM_yyyy = "EEEE MMMM yyyy"
val DD_MMMM     = "dd MMMM"
val DD_MMM      = "dd MMM"
val hh_mm_a     = "hh:mm a"
val hh          = "hh"
val HH          = "HH"
val mm          = "mm"
val a           = "a"
val ss          = "ss"
val HH_mm_ss    = "HH:mm:ss"
val hh_mm_ss_a  = "hh:mm:ss a"
val DD          = "dd"
val MMM         = "MMM"
val MM          = "MM"
val yyyy        = "yyyy"
val yyyy_MMMM   = "yyyy MMMM"

fun String.conjoinixDate(from : String = Default,
                     to  : String = "hh:mm a DD MMM yy") : String
{

    return   SimpleDateFormat(to, Locale.getDefault()).format(this.toDate(from))
}
fun String.toAmPmTime(from : String = HH_mm_ss,
                         to  : String = hh_mm_a) : String
{

    return   SimpleDateFormat(to, Locale.getDefault()).format(this.toDate(from))
}
fun String.toAmPmDate(from : String = Default,
                      to  : String = hh_mm_ss_a_DD_MMM_yyyy) : String
{

    return   SimpleDateFormat(to, Locale.getDefault()).format(this.toDate(from))
}



fun String.toDate(format : String = Default) : Date
{

    return   SimpleDateFormat(format, Locale.getDefault()).parse(this)
}

fun Date.toStringDate(format : String = Default) : String
{

    return   SimpleDateFormat(format, Locale.getDefault()).format(this)
}




fun nowDate(): String
{
    return Date().toStringDate(yyyy_MM_DD)
}




fun nowDateTime(): String
{
    return Date().toStringDate(Default)
}



fun Date.dateDifferenceInDates(to : Date) : Long
{
    return (this.time - to.time)
}


fun String.dateDifferenceInString(to : String) : Long {

   return this.toDate().dateDifferenceInDates(to.toDate())
}





fun String.timeAgo(format: String = Default): String {


    val endDate = nowDateTime().toDate()
    val startDate = this.toDate(format) // this function work with this formate yyyy-MM-dd HH:mm:ss
    val different = endDate.time - startDate.time
    return when {
        different < MINUTE_MILLIS -> "Just Now"
        different < 2 * MINUTE_MILLIS -> "1 minute ago"
        different < 50 * MINUTE_MILLIS -> "${different / MINUTE_MILLIS} minutes ago"
        different < 90 * MINUTE_MILLIS -> "1 hour ago"
        different < 24 * HOUR_MILLIS ->"${different / HOUR_MILLIS} hours ago"
        different < 48 * HOUR_MILLIS -> "Yesterday"
        different < 7 * DAY_MILLIS -> "${different / DAY_MILLIS} days ago"
        different < 2 * WEEKS_MILLIS -> "${different / WEEKS_MILLIS} week ago"
        different < 3.5 * WEEKS_MILLIS -> "${different / WEEKS_MILLIS} weeks ago"
        else -> startDate.toStringDate()
    }
}

fun  Long.convertToReadable(isWithUnits : Boolean = true) : String {
    val seconds = this / 1000
    val minutes = seconds / 60
    val hours = minutes / 60
    val days = hours / 24



    if(isWithUnits){
        return when {
            days > 0 -> "$days d : ${hours%24} h"
            hours > 0 -> "${hours%24} h : ${(minutes%60).correct()} m"
            minutes > 0 -> "${(minutes%60).correct()} m : ${(seconds%60).correct()} s"
            else -> "${(seconds%60).correct()} secs"
        }
    }
    return when {
        days > 0 -> "$days:${hours%24}"
        hours > 0 -> "${hours%24}:${(minutes%60).correct()}"
        minutes > 0 -> "${(minutes%60).correct()}:${(seconds%60).correct()}"
        else -> (seconds%60).correct()
    }



}

fun Long.correct() : String {

    return if(this > 9) {
        "$this"
    }else{
        "0$this"
    }
}