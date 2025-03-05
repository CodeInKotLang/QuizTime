package com.synac.quiztime.presentation.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synac.quiztime.data.mapper.toQuizTopics
import com.synac.quiztime.data.remote.HttpClientFactory
import com.synac.quiztime.data.remote.KtorRemoteQuizDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {

    private val _state = MutableStateFlow(DashboardState())
    val state = _state.asStateFlow()

    private val httpClient = HttpClientFactory.create()
    private val remoteDataSource = KtorRemoteQuizDataSource(httpClient)

    init {
        getQuizTopics()
    }



    private fun getQuizTopics() {
        viewModelScope.launch {
            val quizTopicsDto = remoteDataSource.getQuizTopics()
            if (quizTopicsDto != null) {
                _state.update {
                    it.copy(quizTopics = quizTopicsDto.toQuizTopics())
                }
            }
        }
    }


}