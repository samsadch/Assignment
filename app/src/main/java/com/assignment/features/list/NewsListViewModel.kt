package com.assignment.features.list

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.utils.isNetworkAvailable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * @Author: Samsad Chalil Valappil
 * @Date: 14/08/2022
 */
@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val retrofit: Retrofit
) : ViewModel() {


    fun loadData(context: Context) = viewModelScope.launch {
        if (isNetworkAvailable(context)) {
            //get data from api
        } else {
            //get data from dab- if no data show retry button
        }
    }

}