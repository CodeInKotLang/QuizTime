package com.synac.quiztime.data.remote

import com.synac.quiztime.data.remote.dto.QuizQuestionDto
import com.synac.quiztime.data.remote.dto.QuizTopicDto

interface RemoteQuizDataSource {

    suspend fun getQuizTopics(): List<QuizTopicDto>?

    suspend fun getQuizQuestions(): List<QuizQuestionDto>?

}