package com.assignment.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * @Author: Samsad Chalil Valappil
 * @Date: 16/08/2022
 */

val myLocale: Locale = Locale.US

fun getDate(milliSeconds: Long?, dateFormat: String?): Date {
    val formatter = SimpleDateFormat(dateFormat, myLocale)
    val calendar: Calendar = Calendar.getInstance(myLocale)
    calendar.timeInMillis = milliSeconds!!
    return formatter.parse(formatter.format(calendar.time))!!
}

fun getDate(dateString: String, dateFormat: String?): Date? {
    try {
        val format = SimpleDateFormat(dateFormat, myLocale)
        return format.parse(dateString)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return null
}

fun getDateString(milliSeconds: Long, dateFormat: String?): String {
    // Create a DateFormatter object for displaying date in specified format.
    val formatter = SimpleDateFormat(dateFormat, myLocale)
    // Create a calendar object that will convert the date and time value in milliseconds to date.
    val calendar: Calendar = Calendar.getInstance(myLocale)
    calendar.timeInMillis = milliSeconds
    return formatter.format(calendar.time)
}
