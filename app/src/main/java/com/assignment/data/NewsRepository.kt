package com.assignment.data

import androidx.room.withTransaction
import com.assignment.api.NYApi
import com.assignment.api.NYApi.Companion.api_key
import com.assignment.api.models.NewsResponse
import com.assignment.data.db.NewsData
import com.assignment.data.db.NewsDatabase
import com.assignment.utils.Resource
import com.assignment.utils.networkBoundResource
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * @Author: Samsad Chalil Valappil
 * @Date: 14/08/2022
 */
class NewsRepository @Inject constructor(
    private val newsApi: NYApi,
    private val newsDb: NewsDatabase
) {

    private val newsDao = newsDb.newsDao()

    fun getNews(
        time: Int,
        forceRefresh: Boolean = true,
        onFetchSuccess: () -> Unit,
        onFetchFailed: (Throwable) -> Unit
    ): Flow<Resource<List<NewsData>>> =
        networkBoundResource(
            query = {
                newsDao.getAllNews("")
            },
            fetch = {
                val response = newsApi.getArticles("all-sections", time, api_key)
                response
            },
            saveFetchResult = { newsResponse: NewsResponse ->
                val popularNews = newsResponse.results.map { serverNews ->
                    serverNews.returnNews()
                }
                newsDb.withTransaction {
                    newsDao.deleteNews()
                    newsDao.insertNews(popularNews)
                }
            },
            onFetchSuccess = onFetchSuccess,
            onFetchFailed = { t ->
                if (t !is HttpException && t !is IOException) {
                    throw t
                }
                onFetchFailed(t)
            }
        )

    fun getNewsFromDatabase(query: String) = newsDao.getAllNews(query)

    suspend fun getNewsById(newsId: Long) = newsDao.getNewsById(newsId)
}