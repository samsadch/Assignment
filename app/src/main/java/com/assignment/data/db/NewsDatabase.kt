package com.assignment.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * @Author: Samsad Chalil Valappil
 * @Date: 15/08/2022
 */
@Database(entities = [NewsData::class], version = 1, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao
}