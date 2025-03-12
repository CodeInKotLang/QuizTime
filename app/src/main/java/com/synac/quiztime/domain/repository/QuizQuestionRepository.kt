package com.synac.quiztime.domain.repository

import com.synac.quiztime.domain.model.QuizQuestion
import com.synac.quiztime.domain.util.DataError
import com.synac.quiztime.domain.util.Result

interface QuizQuestionRepository {

    suspend fun getQuizQuestions(topicCode: Int): Result<List<QuizQuestion>, DataError>

}