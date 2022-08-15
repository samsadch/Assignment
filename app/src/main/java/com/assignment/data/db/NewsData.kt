package com.assignment.data.db

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * @Author: Samsad Chalil Valappil
 * @Date: 14/08/2022
 */
@Parcelize
@Entity(tableName = "table_news")
data class NewsData(
    @PrimaryKey
    var id: Long = 0L,
    var abstract: String? = "",
    var asset_id: Long = 0,
    var byline: String? = "",
    var column: String? = "",
    var nytdsection: String? = "",
    var published_date: String? = "",
    var section: String? = "",
    var source: String? = "",
    var subsection: String? = "",
    var title: String? = "",
    var type: String? = "",
    var updated: String? = "",
    var uri: String? = "",
    var url: String? = "",
    var thumbnail: String? = "",
    var threeByTwo: String? = "",
) : Parcelable