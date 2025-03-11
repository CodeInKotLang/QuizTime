package com.synac.quiztime.data.repository

import com.synac.quiztime.data.local.dao.QuizTopicDao
import com.synac.quiztime.data.mapper.entityToQuizTopics
import com.synac.quiztime.data.mapper.toQuizTopics
import com.synac.quiztime.data.mapper.toQuizTopicsEntity
import com.synac.quiztime.data.remote.RemoteQuizDataSource
import com.synac.quiztime.domain.model.QuizTopic
import com.synac.quiztime.domain.repository.QuizTopicRepository

class QuizTopicRepositoryImpl(
    private val remoteDataSource: RemoteQuizDataSource,
    private val topicDao: QuizTopicDao
): QuizTopicRepository {

    override suspend fun getQuizTopics(): List<QuizTopic>? {
        val quizTopicsDto = remoteDataSource.getQuizTopics()
        return if (quizTopicsDto != null) {
            topicDao.clearAllQuizTopics()
            topicDao.insertQuizTopics(quizTopicsDto.toQuizTopicsEntity())
            quizTopicsDto.toQuizTopics()
        } else {
            val cachedTopic = topicDao.getAllQuizTopics()
            if (cachedTopic.isNotEmpty()) {
                cachedTopic.entityToQuizTopics()
            } else {
                null
            }
        }
    }

}