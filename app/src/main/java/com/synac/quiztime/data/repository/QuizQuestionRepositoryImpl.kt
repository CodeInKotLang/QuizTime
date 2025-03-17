package com.synac.quiztime.data.repository

import com.synac.quiztime.data.local.dao.QuizQuestionDao
import com.synac.quiztime.data.local.dao.UserAnswerDao
import com.synac.quiztime.data.mapper.entityToQuizQuestion
import com.synac.quiztime.data.mapper.entityToQuizQuestions
import com.synac.quiztime.data.mapper.toQuizQuestions
import com.synac.quiztime.data.mapper.toQuizQuestionsEntity
import com.synac.quiztime.data.mapper.toUserAnswers
import com.synac.quiztime.data.mapper.toUserAnswersEntity
import com.synac.quiztime.data.remote.RemoteQuizDataSource
import com.synac.quiztime.domain.model.QuizQuestion
import com.synac.quiztime.domain.model.UserAnswer
import com.synac.quiztime.domain.repository.QuizQuestionRepository
import com.synac.quiztime.domain.util.DataError
import com.synac.quiztime.domain.util.Result

class QuizQuestionRepositoryImpl(
    private val remoteDataSource: RemoteQuizDataSource,
    private val questionDao: QuizQuestionDao,
    private val answerDao: UserAnswerDao
) : QuizQuestionRepository {

    override suspend fun fetchAndSaveQuizQuestions(topicCode: Int): Result<List<QuizQuestion>, DataError> {
        return when (val result = remoteDataSource.getQuizQuestions(topicCode)) {
            is Result.Success -> {
                val questionsDto = result.data
                answerDao.clearAllUserAnswers()
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

    override suspend fun getQuizQuestionById(questionId: String): Result<QuizQuestion, DataError> {
        return try {
            val questionEntity = questionDao.getQuizQuestionById(questionId)
            if (questionEntity != null) {
                Result.Success(questionEntity.entityToQuizQuestion())
            } else {
                Result.Failure(DataError.Unknown(errorMessage = "Quiz Question not found"))
            }
        } catch (e: Exception) {
            Result.Failure(DataError.Unknown(e.message))
        }
    }

    override suspend fun saveUserAnswers(userAnswers: List<UserAnswer>): Result<Unit, DataError> {
        return try {
            val answersEntity = userAnswers.toUserAnswersEntity()
            answerDao.insertUserAnswers(answersEntity)
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Failure(DataError.Unknown(e.message))
        }
    }

    override suspend fun getUserAnswers(): Result<List<UserAnswer>, DataError> {
        return try {
            val answersEntity = answerDao.getAllUserAnswers()
            if (answersEntity.isNotEmpty()) {
                Result.Success(answersEntity.toUserAnswers())
            } else {
                Result.Failure(DataError.Unknown(errorMessage = "No User Answers found"))
            }
        } catch (e: Exception) {
            Result.Failure(DataError.Unknown(e.message))
        }
    }
}