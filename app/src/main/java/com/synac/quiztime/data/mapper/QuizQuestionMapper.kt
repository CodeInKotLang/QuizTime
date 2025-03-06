package com.synac.quiztime.data.mapper

import com.synac.quiztime.data.remote.dto.QuizQuestionDto
import com.synac.quiztime.domain.model.QuizQuestion

fun QuizQuestionDto.toQuizQuestion() = QuizQuestion(
    id = id,
    topicCode = topicCode,
    question = question,
    correctAnswer = correctAnswer,
    allOptions = (incorrectAnswers + correctAnswer).shuffled(),
    explanation = explanation
)

fun List<QuizQuestionDto>.toQuizQuestions() = map { it.toQuizQuestion() }