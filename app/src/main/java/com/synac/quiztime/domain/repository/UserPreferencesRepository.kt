package com.synac.quiztime.domain.repository

import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepository {

    fun getQuestionsAttempted(): Flow<Int>

    fun getCorrectAnswers(): Flow<Int>

    fun getUsername(): Flow<String>

    suspend fun saveScore(questionAttempted: Int, correctAnswers: Int)

    suspend fun saveUsername(name: String)

}