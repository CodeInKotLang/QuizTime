package com.synac.quiztime.data.mapper

import com.synac.quiztime.data.remote.dto.IssueReportDto
import com.synac.quiztime.data.util.toFormattedDateTimeString
import com.synac.quiztime.domain.model.IssueReport

fun IssueReport.toIssueReportDto() = IssueReportDto(
    questionId = questionId,
    issueType = issueType,
    additionalComment = additionalComment,
    userEmail = userEmail,
    timestamp = timestampMillis.toFormattedDateTimeString()
)