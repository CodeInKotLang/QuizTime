package com.synac.quiztime.presentation.result

import com.synac.quiztime.domain.model.QuizQuestion
import com.synac.quiztime.domain.model.UserAnswer

data class ResultState(
    val scorePercentage: Int = 0,
    val correctAnswerCount: Int = 0,
    val totalQuestions: Int = 0,
    val quizQuestions: List<QuizQuestion> = emptyList(),
    val userAnswers: List<UserAnswer> = emptyList(),
)
