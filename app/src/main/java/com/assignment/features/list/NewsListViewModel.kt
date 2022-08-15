package com.assignment.features.list

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.data.NewsRepository
import com.assignment.utils.Resource
import com.assignment.utils.isNetworkAvailable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @Author: Samsad Chalil Valappil
 * @Date: 14/08/2022
 */
@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    private val _query = MutableStateFlow("")

    private val newsEventChannelFlow = Channel<NewsEvent>()
    val newsEvent = newsEventChannelFlow.receiveAsFlow()

    val news = _query.flatMapLatest {
        repository.getNewsFromDatabase(it)
    }.stateIn(viewModelScope, SharingStarted.Eagerly, null)


    init {
        viewModelScope.launch {
            getNews(1)
        }
    }

    fun getNews(time: Int) = viewModelScope.launch {
        repository.getNews(time, true, onFetchFailed = {
            showMessage(it.message.toString())
        },
            onFetchSuccess = {

            }).collect {
            when (it) {
                is Resource.Error -> {
                    showProgress(false)
                }
                is Resource.Loading -> {
                    showProgress(true)
                }
                is Resource.Success -> {
                    showProgress(false)
                }
            }
        }
    }


    private fun showMessage(message: String) = viewModelScope.launch {
        newsEventChannelFlow.send(NewsEvent.ShowMessage(message))
    }

    private fun showProgress(isShow: Boolean) = viewModelScope.launch {
        newsEventChannelFlow.send(NewsEvent.ShowProgress(isShow))
    }

    fun setQuery(query: String) {
        _query.value = query
    }

    fun loadData(context: Context) = viewModelScope.launch {
        if (isNetworkAvailable(context)) {
            //get data from api
        } else {
            //get data from dab- if no data show retry button
        }
    }

    sealed class NewsEvent {
        data class ShowMessage(val message: String) : NewsEvent()
        data class ShowProgress(val message: Boolean) : NewsEvent()
    }

}