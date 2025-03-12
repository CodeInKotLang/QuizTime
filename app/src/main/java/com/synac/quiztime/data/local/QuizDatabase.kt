package com.synac.quiztime.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.synac.quiztime.data.local.converter.OptionListConverters
import com.synac.quiztime.data.local.dao.QuizQuestionDao
import com.synac.quiztime.data.local.dao.QuizTopicDao
import com.synac.quiztime.data.local.entity.QuizQuestionEntity
import com.synac.quiztime.data.local.entity.QuizTopicEntity

@Database(
    version = 2,
    entities = [
        QuizTopicEntity::class,
        QuizQuestionEntity::class
    ]
)
@TypeConverters(
    OptionListConverters::class
)
abstract class QuizDatabase : RoomDatabase() {

    abstract fun quizTopicDao(): QuizTopicDao

    abstract fun quizQuestionDao(): QuizQuestionDao

}