package com.synac.quiztime.presentation.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import com.synac.quiztime.domain.model.QuizQuestion
import com.synac.quiztime.domain.model.UserAnswer
import com.synac.quiztime.presentation.common_component.ErrorScreen
import com.synac.quiztime.presentation.quiz.component.ExitQuizDialog
import com.synac.quiztime.presentation.quiz.component.QuizScreenLoadingContent
import com.synac.quiztime.presentation.quiz.component.QuizScreenTopBar
import com.synac.quiztime.presentation.quiz.component.QuizSubmitButtons
import com.synac.quiztime.presentation.quiz.component.SubmitQuizDialog

@Composable
fun QuizScreen(
    state: QuizState
) {

    SubmitQuizDialog(
        isOpen = state.isSubmitQuizDialogOpen,
        onDialogDismiss = {},
        onConfirmButtonClick = {}
    )

    ExitQuizDialog(
        isOpen = state.isExitQuizDialogOpen,
        onDialogDismiss = {},
        onConfirmButtonClick = {}
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        QuizScreenTopBar(
            title = state.topBarTitle,
            onExitQuizButtonClick = {}
        )
        if (state.isLoading) {
            QuizScreenLoadingContent(
                modifier = Modifier.fillMaxSize(),
                loadingMessage = state.loadingMessage
            )
        } else {
            when {
                state.errorMessage != null -> {
                    ErrorScreen(
                        modifier = Modifier.fillMaxSize(),
                        errorMessage = state.errorMessage,
                        onRefreshIconClick = {}
                    )
                }

                state.questions.isEmpty() -> {
                    ErrorScreen(
                        modifier = Modifier.fillMaxSize(),
                        errorMessage = "No Quiz Question Available",
                        onRefreshIconClick = {}
                    )
                }

                else -> {
                    QuizScreenContent(
                        state = state
                    )
                }
            }
        }

    }

}

@Composable
private fun QuizScreenContent(
    modifier: Modifier = Modifier,
    state: QuizState
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        QuestionNavigationRow(
            currentQuestionIndex = state.currentQuestionIndex,
            questions = state.questions,
            answers = state.answers,
            onTabSelected = {}
        )
        Spacer(modifier = Modifier.height(20.dp))
        QuestionItem(
            modifier = Modifier
                .weight(1f)
                .padding(15.dp)
                .verticalScroll(rememberScrollState()),
            currentQuestionIndex = state.currentQuestionIndex,
            questions = state.questions,
            answers = state.answers,
            onOptionSelected = { _, _ -> }
        )
        QuizSubmitButtons(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            isPreviousButtonEnable = state.currentQuestionIndex != 0,
            isNextButtonEnable = state.currentQuestionIndex != state.questions.lastIndex,
            onPreviousButtonClick = {},
            onNextButtonClick = {},
            onSubmitButtonClick = {}
        )
    }
}

@Composable
private fun QuestionNavigationRow(
    modifier: Modifier = Modifier,
    currentQuestionIndex: Int,
    questions: List<QuizQuestion>,
    answers: List<UserAnswer>,
    onTabSelected: (Int) -> Unit
) {
    ScrollableTabRow(
        modifier = modifier,
        selectedTabIndex = currentQuestionIndex,
        edgePadding = 0.dp
    ) {
        questions.forEachIndexed { index, question ->
            val containerColor = when {
                answers.any { it.questionId == question.id } -> {
                    MaterialTheme.colorScheme.secondaryContainer
                }

                else -> MaterialTheme.colorScheme.surface
            }
            Tab(
                modifier = Modifier.background(containerColor),
                selected = currentQuestionIndex == index,
                onClick = { onTabSelected(index) }
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 10.dp),
                    text = "${index + 1}"
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun QuestionItem(
    modifier: Modifier = Modifier,
    currentQuestionIndex: Int,
    questions: List<QuizQuestion>,
    answers: List<UserAnswer>,
    onOptionSelected: (String, String) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        val currentQuestion = questions[currentQuestionIndex]
        val selectedAnswer =
            answers.find { it.questionId == currentQuestion.id }?.selectedOption
        Text(
            text = currentQuestion.question,
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(10.dp))
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            currentQuestion.allOptions.forEach { option ->
                OptionItem(
                    modifier = Modifier
                        .widthIn(min = 400.dp)
                        .padding(vertical = 10.dp),
                    option = option,
                    isSelected = option == selectedAnswer,
                    onClick = { onOptionSelected(currentQuestion.id, option) }
                )
            }
        }
    }
}

@Composable
private fun OptionItem(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    option: String,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .clickable { onClick() }
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = MaterialTheme.shapes.small
            ),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) {
                MaterialTheme.colorScheme.primaryContainer
            } else MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = isSelected,
                onClick = onClick
            )
            Text(
                text = option,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}


//@Preview(showBackground = true)
@PreviewScreenSizes
@Composable
private fun PreviewQuizScreen() {
    val dummyQuestions = List(size = 10) { index ->
        QuizQuestion(
            id = "$index",
            topicCode = 1,
            question = "What is the language for Android Dev?",
            allOptions = listOf("Java", "Python", "Dart", "Kotlin"),
            correctAnswer = "Kotlin",
            explanation = "Some Explanation"
        )
    }
    val dummyAnswers = listOf(
        UserAnswer(questionId = "1", selectedOption = ""),
        UserAnswer(questionId = "3", selectedOption = ""),
    )
    QuizScreen(
        state = QuizState(
            questions = dummyQuestions,
            answers = dummyAnswers
        )
    )
}