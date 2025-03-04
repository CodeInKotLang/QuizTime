package com.synac.quiztime.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Route {
    @Serializable
    data object DashboardScreen : Route
    @Serializable
    data class QuizScreen(val topicCode: Int) : Route
    @Serializable
    data object ResultScreen : Route
    @Serializable
    data class IssueReportScreen(val questionId: String) : Route
}