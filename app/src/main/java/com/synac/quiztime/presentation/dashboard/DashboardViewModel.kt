package com.synac.quiztime.presentation.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synac.quiztime.data.repository.QuizTopicRepositoryImpl
import com.synac.quiztime.domain.repository.QuizTopicRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {

    private val _state = MutableStateFlow(DashboardState())
    val state = _state.asStateFlow()

    private val quizTopicRepository: QuizTopicRepository = QuizTopicRepositoryImpl()


    init {
        getQuizTopics()
    }



    private fun getQuizTopics() {
        viewModelScope.launch {
            val quizTopics = quizTopicRepository.getQuizTopics()
            if (quizTopics != null) {
                _state.update { it.copy(quizTopics = quizTopics) }
            }
        }
    }


}