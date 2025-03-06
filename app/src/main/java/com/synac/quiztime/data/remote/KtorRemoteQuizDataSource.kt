package com.synac.quiztime.data.remote

import com.synac.quiztime.data.remote.dto.QuizQuestionDto
import com.synac.quiztime.data.remote.dto.QuizTopicDto
import com.synac.quiztime.data.util.Constant.BASE_URL
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class KtorRemoteQuizDataSource(
    private val httpClient: HttpClient
) {

    suspend fun getQuizTopics(): List<QuizTopicDto>? {
        return try {
            val response = httpClient.get(urlString = "$BASE_URL/quiz/topics")
            response.body<List<QuizTopicDto>>()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun getQuizQuestions(): List<QuizQuestionDto>? {
        return try {
            val response = httpClient.get(urlString = "$BASE_URL/quiz/questions")
            response.body<List<QuizQuestionDto>>()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}