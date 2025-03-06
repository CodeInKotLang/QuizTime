package com.synac.quiztime.data.repository

import com.synac.quiztime.data.mapper.toQuizTopics
import com.synac.quiztime.data.remote.HttpClientFactory
import com.synac.quiztime.data.remote.KtorRemoteQuizDataSource
import com.synac.quiztime.domain.model.QuizTopic
import com.synac.quiztime.domain.repository.QuizTopicRepository

class QuizTopicRepositoryImpl: QuizTopicRepository {

    private val httpClient = HttpClientFactory.create()
    private val remoteDataSource = KtorRemoteQuizDataSource(httpClient)

    override suspend fun getQuizTopics(): List<QuizTopic>? {
        val quizTopicsDto = remoteDataSource.getQuizTopics()
        return quizTopicsDto?.toQuizTopics()
    }

}