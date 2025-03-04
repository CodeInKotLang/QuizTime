package com.synac.quiztime.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.synac.quiztime.domain.model.QuizQuestion
import com.synac.quiztime.domain.model.QuizTopic
import com.synac.quiztime.presentation.dashboard.DashboardScreen
import com.synac.quiztime.presentation.dashboard.DashboardState
import com.synac.quiztime.presentation.issue_report.IssueReportScreen
import com.synac.quiztime.presentation.issue_report.IssueReportState
import com.synac.quiztime.presentation.quiz.QuizScreen
import com.synac.quiztime.presentation.quiz.QuizState
import com.synac.quiztime.presentation.result.ResultScreen
import com.synac.quiztime.presentation.result.ResultState

@Composable
fun NavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        modifier = Modifier.padding(paddingValues),
        navController = navController,
        startDestination = Route.DashboardScreen
    ) {
        val dummyQuestions = List(size = 10) { index ->
            QuizQuestion(
                id = "$index",
                topicCode = 1,
                question = "What is the language for Android Dev?",
                allOptions = listOf("Java", "Python", "Dart", "Kotlin"),
                correctAnswer = "Kotlin",
                explanation = "Some Explanation"
            )
        }
        composable<Route.DashboardScreen> {
            val dummyTopics = List(size = 20) { index ->
                QuizTopic(id = "1", name = "Android $index", imageUrl = "", code = index)
            }
            DashboardScreen(
                state = DashboardState(quizTopics = dummyTopics),
                onTopicCardClick = { topicCode ->
                    navController.navigate(Route.QuizScreen(topicCode))
                }
            )
        }
        composable<Route.QuizScreen> {
            val topicCode = it.toRoute<Route.QuizScreen>().topicCode
            QuizScreen(
                state = QuizState(
                    topBarTitle = "Topic Code -> $topicCode",
                    questions = dummyQuestions
                ),
                navigationToDashboardScreen = {
                    navController.navigateUp()
                },
                navigationToResultScreen = {
                    navController.navigate(Route.ResultScreen) {
                        popUpTo<Route.QuizScreen> { inclusive = true }
                    }
                }
            )
        }
        composable<Route.ResultScreen> {
            ResultScreen(
                state = ResultState(quizQuestions = dummyQuestions),
                onReportIconClick = { questionId ->
                    navController.navigate(Route.IssueReportScreen(questionId))
                },
                onStartNewQuiz = {
                    navController.navigate(Route.DashboardScreen) {
                        popUpTo<Route.ResultScreen> { inclusive = true }
                    }
                }
            )
        }
        composable<Route.IssueReportScreen> {
            IssueReportScreen(
                state = IssueReportState(
                    quizQuestion = dummyQuestions[0]
                ),
                onBackButtonClick = {
                    navController.navigateUp()
                }
            )
        }
    }
}