package com.synac.quiztime.data.repository

import com.synac.quiztime.data.mapper.toQuizQuestions
import com.synac.quiztime.data.remote.RemoteQuizDataSource
import com.synac.quiztime.domain.model.QuizQuestion
import com.synac.quiztime.domain.repository.QuizQuestionRepository

class QuizQuestionRepositoryImpl(
    private val remoteDataSource: RemoteQuizDataSource
): QuizQuestionRepository {

    override suspend fun getQuizQuestions(): List<QuizQuestion>? {
        val quizQuestionsDto = remoteDataSource.getQuizQuestions()
        return quizQuestionsDto?.toQuizQuestions()
    }
}