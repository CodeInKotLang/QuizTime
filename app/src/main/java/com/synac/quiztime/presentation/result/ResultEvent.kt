package com.synac.quiztime.presentation.result

sealed interface ResultEvent {
    data class ShowToast(val message: String) : ResultEvent
}