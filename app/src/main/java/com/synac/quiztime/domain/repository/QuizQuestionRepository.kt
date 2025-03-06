package com.synac.quiztime.domain.repository

import com.synac.quiztime.domain.model.QuizQuestion

interface QuizQuestionRepository {

    suspend fun getQuizQuestions(): List<QuizQuestion>?

}