package com.synac.quiztime.data.repository

import com.synac.quiztime.data.mapper.toIssueReportDto
import com.synac.quiztime.data.remote.RemoteQuizDataSource
import com.synac.quiztime.domain.model.IssueReport
import com.synac.quiztime.domain.repository.IssueReportRepository
import com.synac.quiztime.domain.util.DataError
import com.synac.quiztime.domain.util.Result

class IssueReportRepositoryImpl(
    private val remoteDataSource: RemoteQuizDataSource
): IssueReportRepository {

    override suspend fun insertIssueReport(
        report: IssueReport
    ): Result<Unit, DataError> {
        val reportDto = report.toIssueReportDto()
        return remoteDataSource.insertIssueReport(reportDto)
    }

}