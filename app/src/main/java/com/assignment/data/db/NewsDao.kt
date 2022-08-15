package com.assignment.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * @Author: Samsad Chalil Valappil
 * @Date: 14/08/2022
 */
@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(employeeList: List<NewsData>)

    @Query("DELETE FROM table_news")
    suspend fun deleteNews()


    @Query("SELECT * FROM table_news  WHERE title LIKE '%' ||  :query || '%' OR byline LIKE '%' ||  :query || '%' ORDER BY updated ASC")
    fun getAllNews(query: String): Flow<List<NewsData>>


    @Query("SELECT COUNT(*) FROM table_news")
    suspend fun getCount(): Int
}