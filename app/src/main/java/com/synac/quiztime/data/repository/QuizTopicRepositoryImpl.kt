package com.synac.quiztime.data.repository

import com.synac.quiztime.data.mapper.toQuizTopics
import com.synac.quiztime.data.remote.RemoteQuizDataSource
import com.synac.quiztime.domain.model.QuizTopic
import com.synac.quiztime.domain.repository.QuizTopicRepository

class QuizTopicRepositoryImpl(
    private val remoteDataSource: RemoteQuizDataSource
): QuizTopicRepository {

    override suspend fun getQuizTopics(): List<QuizTopic>? {
        val quizTopicsDto = remoteDataSource.getQuizTopics()
        return quizTopicsDto?.toQuizTopics()
    }

}