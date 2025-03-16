package com.synac.quiztime.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.synac.quiztime.data.local.converter.OptionListConverters
import com.synac.quiztime.data.local.dao.QuizQuestionDao
import com.synac.quiztime.data.local.dao.QuizTopicDao
import com.synac.quiztime.data.local.dao.UserAnswerDao
import com.synac.quiztime.data.local.entity.QuizQuestionEntity
import com.synac.quiztime.data.local.entity.QuizTopicEntity
import com.synac.quiztime.data.local.entity.UserAnswerEntity

@Database(
    version = 3,
    entities = [
        QuizTopicEntity::class,
        QuizQuestionEntity::class,
        UserAnswerEntity::class
    ]
)
@TypeConverters(
    OptionListConverters::class
)
abstract class QuizDatabase : RoomDatabase() {

    abstract fun quizTopicDao(): QuizTopicDao

    abstract fun quizQuestionDao(): QuizQuestionDao

    abstract fun userAnswerDao(): UserAnswerDao

}