package com.synac.quiztime.data.repository

import com.synac.quiztime.data.local.dao.QuizQuestionDao
import com.synac.quiztime.data.mapper.entityToQuizQuestions
import com.synac.quiztime.data.mapper.toQuizQuestions
import com.synac.quiztime.data.mapper.toQuizQuestionsEntity
import com.synac.quiztime.data.remote.RemoteQuizDataSource
import com.synac.quiztime.domain.model.QuizQuestion
import com.synac.quiztime.domain.repository.QuizQuestionRepository
import com.synac.quiztime.domain.util.DataError
import com.synac.quiztime.domain.util.Result

class QuizQuestionRepositoryImpl(
    private val remoteDataSource: RemoteQuizDataSource,
    private val questionDao: QuizQuestionDao
) : QuizQuestionRepository {

    override suspend fun fetchAndSaveQuizQuestions(topicCode: Int): Result<List<QuizQuestion>, DataError> {
        return when (val result = remoteDataSource.getQuizQuestions(topicCode)) {
            is Result.Success -> {
                val questionsDto = result.data
                questionDao.clearAllQuizQuestions()
                questionDao.insertQuizQuestions(questionsDto.toQuizQuestionsEntity())
                Result.Success(questionsDto.toQuizQuestions())
            }

            is Result.Failure -> result
        }
    }

    override suspend fun getQuizQuestions(): Result<List<QuizQuestion>, DataError> {
        return try {
            val questionsEntity = questionDao.getAllQuizQuestions()
            if (questionsEntity.isNotEmpty()) {
                Result.Success(questionsEntity.entityToQuizQuestions())
            } else {
                Result.Failure(DataError.Unknown(errorMessage = "No Quiz Questions Found."))
            }
        } catch (e: Exception) {
            Result.Failure(DataError.Unknown(e.message))
        }
    }
}