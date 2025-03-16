package com.synac.quiztime.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.synac.quiztime.data.util.Constant.USER_ANSWER_TABLE_NAME

@Entity(tableName = USER_ANSWER_TABLE_NAME)
data class UserAnswerEntity(
    @PrimaryKey
    val questionId: String,
    val selectedOption: String
)
