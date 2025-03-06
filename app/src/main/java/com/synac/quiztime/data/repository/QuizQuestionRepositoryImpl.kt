package com.synac.quiztime.data.repository

import com.synac.quiztime.data.mapper.toQuizQuestions
import com.synac.quiztime.data.remote.HttpClientFactory
import com.synac.quiztime.data.remote.KtorRemoteQuizDataSource
import com.synac.quiztime.domain.model.QuizQuestion
import com.synac.quiztime.domain.repository.QuizQuestionRepository

class QuizQuestionRepositoryImpl: QuizQuestionRepository {

    private val httpClient = HttpClientFactory.create()
    private val remoteDataSource = KtorRemoteQuizDataSource(httpClient)

    override suspend fun getQuizQuestions(): List<QuizQuestion>? {
        val quizQuestionsDto = remoteDataSource.getQuizQuestions()
        return quizQuestionsDto?.toQuizQuestions()
    }
}