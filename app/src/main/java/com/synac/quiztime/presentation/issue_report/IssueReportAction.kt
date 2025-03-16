package com.synac.quiztime.presentation.issue_report

sealed interface IssueReportAction {
    data object ExpandQuestionCard : IssueReportAction
    data class SetIssueReportType(val issueType: IssueType) : IssueReportAction
    data class SetOtherIssueText(val otherIssueText: String) : IssueReportAction
    data class SetAdditionComment(val additionComment: String) : IssueReportAction
    data class SetEmailForFollowUp(val emailForFollowUp: String) : IssueReportAction
    data object SubmitReport : IssueReportAction
}