package com.assignment.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

/**
 * @Author: Samsad Chalil Valappil
 * @Date: 15/08/2022
 */
@Database(entities = [NewsData::class], version = 1, exportSchema = false)
@TypeConverters(DataTypeConverters::class)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao
}