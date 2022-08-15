package com.assignment.api.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * @Author: Samsad Chalil Valappil
 * @Date: 14/08/2022
 */

@Parcelize
data class Media(
    val approved_for_syndication: Int,
    val caption: String,
    val copyright: String,
    @SerializedName("media-metadata")
    val mediaMetadata: List<MediaMetadata>,
    val subtype: String,
    val type: String
) : Parcelable