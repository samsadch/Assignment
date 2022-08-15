package com.assignment.api.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @Author: Samsad Chalil Valappil
 * @Date: 14/08/2022
 */
@Parcelize
data class MediaMetadata(
    val format: String,
    val height: Int,
    val url: String,
    val width: Int
) : Parcelable