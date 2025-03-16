package com.synac.quiztime.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class IssueReportDto(
    val questionId: String,
    val issueType: String,
    val additionalComment: String?,
    val userEmail: String?,
    val timestamp: String
)