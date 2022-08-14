package com.assignment.utils

import android.content.Context
import android.net.ConnectivityManager

/**
 * @Author: Samsad Chalil Valappil
 * @Date: 14/08/2022
 */

fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectivityManager.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
}