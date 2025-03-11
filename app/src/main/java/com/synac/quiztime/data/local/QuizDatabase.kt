package com.synac.quiztime.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.synac.quiztime.data.local.dao.QuizTopicDao
import com.synac.quiztime.data.local.entity.QuizTopicEntity

@Database(
    version = 1,
    entities = [QuizTopicEntity::class]
)
abstract class QuizDatabase: RoomDatabase() {

    abstract fun quizTopicDao(): QuizTopicDao

}