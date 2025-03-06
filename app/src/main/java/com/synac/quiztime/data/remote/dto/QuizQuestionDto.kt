package com.synac.quiztime.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class QuizQuestionDto(
    val id: String,
    val question: String,
    val correctAnswer: String,
    val incorrectAnswers: List<String>,
    val explanation: String,
    val topicCode: Int
)
