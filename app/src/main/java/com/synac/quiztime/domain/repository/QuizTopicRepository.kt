package com.synac.quiztime.domain.repository

import com.synac.quiztime.domain.model.QuizTopic

interface QuizTopicRepository {

    suspend fun getQuizTopics(): List<QuizTopic>?

}