package com.synac.quiztime.data.mapper

import com.synac.quiztime.data.remote.dto.QuizTopicDto
import com.synac.quiztime.data.util.Constant.BASE_URL
import com.synac.quiztime.domain.model.QuizTopic

fun QuizTopicDto.toQuizTopic() = QuizTopic(
    id = id,
    name = name,
    imageUrl = BASE_URL + imageUrl,
    code = code
)

fun List<QuizTopicDto>.toQuizTopics() = map { it.toQuizTopic() }