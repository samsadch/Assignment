package com.assignment.di

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.assignment.api.NYApi
import com.assignment.api.NYApi.Companion.BASE_URL
import com.assignment.data.db.NewsDatabase
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * @Author: Samsad Chalil Valappil
 * @Date: 14/08/2022
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val gson = GsonBuilder().setLenient().create()
        val client = OkHttpClient.Builder().addInterceptor { chain ->
            Log.d("REQUEST",chain.toString())
            val newRequest = chain.request().newBuilder()
                .build()
            chain.proceed(newRequest)
        }.connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    /*Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()*/
    }


    @Provides
    @Singleton
    fun provideNewsApi(retrofit: Retrofit): NYApi =
        retrofit.create(NYApi::class.java)

    @Provides
    @Singleton
    fun provideDatabase(app: Application): NewsDatabase =
        Room.databaseBuilder(app, NewsDatabase::class.java, "ny_db")
            .fallbackToDestructiveMigration()
            .build()

}