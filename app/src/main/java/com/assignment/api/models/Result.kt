package com.assignment.api.models

import android.os.Parcelable
import com.assignment.data.db.NewsData
import kotlinx.android.parcel.Parcelize

/**
 * @Author: Samsad Chalil Valappil
 * @Date: 14/08/2022
 */
@Parcelize
data class Result(
    val abstract: String? = "",
    val adx_keywords: String? = "",
    val asset_id: Long = 0L,
    val byline: String? = "",
    val column: String? = "",
    val eta_id: Int = 0,
    val geo_facet: List<String>,
    val id: Long = 0L,
    val media: List<Media>?,
    val nytdsection: String? = "",
    val org_facet: List<String>,
    val per_facet: List<String>,
    val published_date: String? = "",
    val section: String? = "",
    val source: String? = "",
    val subsection: String? = "",
    val title: String? = "",
    val type: String? = "",
    val updated: String,
    val uri: String? = "",
    val url: String? = ""
) : Parcelable {
    fun returnNews(): NewsData {
        val thumb = if (media?.isNotEmpty() == true) {
            val data = media[0]
            if (data.mediaMetadata.isNotEmpty()) {
                data.mediaMetadata.get(0).url
            } else {
                ""
            }
        } else {
            ""
        }
        return NewsData(
            id = id,
            abstract = abstract,
            asset_id = asset_id,
            byline = byline, column = column,
            nytdsection = nytdsection,
            published_date = published_date,
            section = section,
            source = source,
            subsection = subsection,
            title = title,
            type = type,
            updated = updated,
            uri = uri,
            url = url,
            thumbnail = thumb
        )
    }
}