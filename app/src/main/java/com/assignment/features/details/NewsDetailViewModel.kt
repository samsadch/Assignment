package com.assignment.features.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.data.NewsRepository
import com.assignment.data.db.NewsData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @Author: Samsad Chalil Valappil
 * @Date: 15/08/2022
 */
@HiltViewModel
class NewsDetailViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    private val newsDetailChannelFlow = Channel<NewsDetailEvent>()
    val newsDetailEvent = newsDetailChannelFlow.receiveAsFlow()

    fun getNewsById(newsId: Long) = viewModelScope.launch {
        repository.getNewsById(newsId).collect {
            newsDetailChannelFlow.send(NewsDetailEvent.OnNewsAvailable(it))
        }
    }

    sealed class NewsDetailEvent {
        data class OnNewsAvailable(val news: NewsData) : NewsDetailEvent()
    }
}