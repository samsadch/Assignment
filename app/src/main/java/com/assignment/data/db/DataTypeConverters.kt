package com.assignment.data.db

import androidx.room.TypeConverter
import com.assignment.api.NYApi
import com.assignment.utils.getDate
import java.util.*

/**
 * @Author: Samsad Chalil Valappil
 * @Date: 16/08/2022
 */
class DataTypeConverters {
    @TypeConverter
    fun fromDateString(dateString: Date?): Long? {
        if (dateString == null) {
            return null
        }

        return dateString.time
    }

    @TypeConverter
    fun toDateString(dateLong: Long?): Date? {
        if (dateLong == null) {
            return null
        }
        return getDate(dateLong, NYApi.PUBLISH_DATE_FORMAT)
    }
}