package com.synac.quiztime.data.remote

import com.synac.quiztime.data.remote.dto.QuizQuestionDto
import com.synac.quiztime.data.remote.dto.QuizTopicDto
import com.synac.quiztime.domain.util.DataError
import com.synac.quiztime.domain.util.Result

interface RemoteQuizDataSource {

    suspend fun getQuizTopics(): Result<List<QuizTopicDto>, DataError>

    suspend fun getQuizQuestions(): Result<List<QuizQuestionDto>, DataError>

}