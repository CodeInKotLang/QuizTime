package com.synac.quiztime.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.synac.quiztime.data.local.entity.QuizQuestionEntity

@Dao
interface QuizQuestionDao {

    @Query("SELECT * FROM quiz_questions")
    suspend fun getAllQuizQuestions(): List<QuizQuestionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuizQuestions(questions: List<QuizQuestionEntity>)

    @Query("DELETE FROM quiz_questions")
    suspend fun clearAllQuizQuestions()

    @Query("SELECT * FROM quiz_questions WHERE id = :questionId")
    suspend fun getQuizQuestionById(questionId: String): QuizQuestionEntity?

}