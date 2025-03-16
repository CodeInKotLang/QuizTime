package com.synac.quiztime.domain.repository

import com.synac.quiztime.domain.model.QuizQuestion
import com.synac.quiztime.domain.model.UserAnswer
import com.synac.quiztime.domain.util.DataError
import com.synac.quiztime.domain.util.Result

interface QuizQuestionRepository {

    suspend fun fetchAndSaveQuizQuestions(topicCode: Int): Result<List<QuizQuestion>, DataError>

    suspend fun getQuizQuestions(): Result<List<QuizQuestion>, DataError>

    suspend fun getQuizQuestionById(questionId: String): Result<QuizQuestion, DataError>

    suspend fun saveUserAnswers(userAnswers: List<UserAnswer>): Result<Unit, DataError>

    suspend fun getUserAnswers(): Result<List<UserAnswer>, DataError>

}