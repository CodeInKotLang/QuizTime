package com.synac.quiztime.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class QuizTopicDto(
    val id: String,
    val name: String,
    val imageUrl: String,
    val code: Int
)
