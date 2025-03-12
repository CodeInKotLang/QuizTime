package com.synac.quiztime.presentation.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synac.quiztime.domain.repository.QuizQuestionRepository
import com.synac.quiztime.domain.util.onFailure
import com.synac.quiztime.domain.util.onSuccess
import com.synac.quiztime.presentation.util.getErrorMessage
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ResultViewModel(
    private val questionRepository: QuizQuestionRepository
): ViewModel() {

    private val _state = MutableStateFlow(ResultState())
    val state = _state.asStateFlow()

    private val _event = Channel<ResultEvent>()
    val event = _event.receiveAsFlow()

    init {
        getAllQuestions()
    }

    private fun getAllQuestions() {
        viewModelScope.launch {
            questionRepository.getQuizQuestions()
                .onSuccess { questions ->
                    _state.update { it.copy(quizQuestions = questions) }
                }
                .onFailure { error ->
                    _event.send(ResultEvent.ShowToast(error.getErrorMessage()))
                }
        }
    }

}