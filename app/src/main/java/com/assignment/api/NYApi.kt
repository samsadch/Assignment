package com.assignment.api

import com.assignment.api.models.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @Author: Samsad Chalil Valappil
 * @Date: 14/08/2022
 */
interface NYApi {

    companion object {
        const val api_key = "oyeXz13H45Uqzjt9vuFUzQsSiHgVEbZ8"
        const val BASE_URL = "http://api.nytimes.com/svc/mostpopular/v2/"
        const val PUBLISH_DATE_FORMAT = "yyyy-MM-dd"
    }

    @GET("mostviewed/{section}/{time-period}.json")
    suspend fun getArticles(
        @Path("section") section: String?,
        @Path("time-period") timePeriod: Int,
        @Query("api-key") apiKey: String?
    ): NewsResponse
}