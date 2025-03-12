package com.synac.quiztime.presentation.quiz

sealed interface QuizAction {
    data object PrevQuestionButtonClick: QuizAction
    data object NextQuestionButtonClick: QuizAction
    data class JumpToQuestion(val index: Int): QuizAction
    data class OnOptionSelected(val questionId: String, val answer: String): QuizAction
    data object SubmitQuizButtonClick: QuizAction
    data object SubmitQuizDialogDismiss: QuizAction
    data object SubmitQuizConfirmButtonClick: QuizAction
    data object ExitQuizButtonClick: QuizAction
    data object ExitQuizDialogDismiss: QuizAction
    data object ExitQuizConfirmButtonClick: QuizAction
    data object Refresh: QuizAction
}