package com.synac.quiztime.presentation.quiz

sealed interface QuizEvent {
    data class ShowToast(val message: String) : QuizEvent
    data object NavigateToResultScreen : QuizEvent
    data object NavigateToDashboardScreen : QuizEvent
}