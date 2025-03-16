package com.synac.quiztime.presentation.issue_report

sealed interface IssueReportEvent {
    data class ShowToast(val message: String) : IssueReportEvent
    data object NavigateUp : IssueReportEvent
}