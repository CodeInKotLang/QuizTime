package com.synac.quiztime.domain.repository

import com.synac.quiztime.domain.model.QuizTopic
import com.synac.quiztime.domain.util.DataError
import com.synac.quiztime.domain.util.Result

interface QuizTopicRepository {

    suspend fun getQuizTopics(): Result<List<QuizTopic>, DataError>

}