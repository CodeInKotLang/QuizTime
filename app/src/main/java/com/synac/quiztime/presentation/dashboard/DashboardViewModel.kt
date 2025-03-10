package com.synac.quiztime.presentation.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synac.quiztime.domain.repository.QuizTopicRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val topicRepository: QuizTopicRepository
) : ViewModel() {

    private val _state = MutableStateFlow(DashboardState())
    val state = _state.asStateFlow()

    init {
        getQuizTopics()
    }



    private fun getQuizTopics() {
        viewModelScope.launch {
            val quizTopics = topicRepository.getQuizTopics()
            if (quizTopics != null) {
                _state.update { it.copy(quizTopics = quizTopics) }
            }
        }
    }


}