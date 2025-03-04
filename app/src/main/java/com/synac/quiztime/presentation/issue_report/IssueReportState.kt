package com.synac.quiztime.presentation.issue_report

import com.synac.quiztime.domain.model.QuizQuestion

data class IssueReportState(
    val quizQuestion: QuizQuestion? = null,
    val isQuestionCardExpanded: Boolean = false,
    val issueType: IssueType = IssueType.OTHER,
    val otherIssueText: String = "",
    val additionComment: String = "",
    val emailForFollowUp: String = "",
)

enum class IssueType(val text: String) {
    INCORRECT_ANSWER(text = "Incorrect Answer"),
    UNCLEAR_QUESTION(text = "Question is Unclear"),
    TYPO_OR_GRAMMAR(text = "Typo or Grammar Mistake"),
    OTHER(text = "Other")
}
