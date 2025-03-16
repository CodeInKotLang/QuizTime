package com.synac.quiztime.domain.repository

import com.synac.quiztime.domain.model.IssueReport
import com.synac.quiztime.domain.util.DataError
import com.synac.quiztime.domain.util.Result

interface IssueReportRepository {

    suspend fun insertIssueReport(report: IssueReport): Result<Unit, DataError>

}