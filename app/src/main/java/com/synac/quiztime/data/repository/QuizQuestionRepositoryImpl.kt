package com.synac.quiztime.data.repository

import com.synac.quiztime.data.mapper.toQuizQuestions
import com.synac.quiztime.data.remote.RemoteQuizDataSource
import com.synac.quiztime.domain.model.QuizQuestion
import com.synac.quiztime.domain.repository.QuizQuestionRepository
import com.synac.quiztime.domain.util.DataError
import com.synac.quiztime.domain.util.Result

class QuizQuestionRepositoryImpl(
    private val remoteDataSource: RemoteQuizDataSource
): QuizQuestionRepository {

    override suspend fun getQuizQuestions(topicCode: Int): Result<List<QuizQuestion>, DataError> {
        return when(val result = remoteDataSource.getQuizQuestions(topicCode)) {
            is Result.Success -> {
                val questionsDto = result.data
                Result.Success(questionsDto.toQuizQuestions())
            }
            is Result.Failure -> result
        }
    }
}