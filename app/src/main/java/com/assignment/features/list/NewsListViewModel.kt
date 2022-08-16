package com.assignment.features.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.data.NewsRepository
import com.assignment.utils.Resource
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
    private val _recentTimePeriod = MutableStateFlow(1)

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

    fun getNews(time: Int = _recentTimePeriod.value) = viewModelScope.launch {
        setRecentTimePeriod(time)
        repository.getNews(time, true, onFetchFailed = {
            showErrorMessage()
        }, onFetchSuccess = {

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


    private fun showErrorMessage() = viewModelScope.launch {
        newsEventChannelFlow.send(NewsEvent.ShowErrorMessage)
    }

    private fun showProgress(isShow: Boolean) = viewModelScope.launch {
        newsEventChannelFlow.send(NewsEvent.ShowProgress(isShow))
    }

    fun setQuery(query: String) {
        _query.value = query
    }

    private fun setRecentTimePeriod(recentValue: Int) {
        _recentTimePeriod.value = recentValue
    }

    sealed class NewsEvent {
        data class ShowMessage(val message: String) : NewsEvent()
        object ShowErrorMessage : NewsEvent()
        data class ShowProgress(val progress: Boolean) : NewsEvent()
    }

}