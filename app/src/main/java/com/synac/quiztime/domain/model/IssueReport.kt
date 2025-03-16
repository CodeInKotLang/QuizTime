package com.synac.quiztime.domain.model

data class IssueReport(
    val questionId: String,
    val issueType: String,
    val additionalComment: String?,
    val userEmail: String?,
    val timestampMillis: Long
)