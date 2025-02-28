package com.synac.quiztime.presentation.quiz.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SubmitQuizDialog(
    modifier: Modifier = Modifier,
    isOpen: Boolean,
    title: String = "Submit Quiz",
    confirmButtonText: String = "Submit",
    dismissButtonText: String = "Cancel",
    onDialogDismiss: () -> Unit,
    onConfirmButtonClick: () -> Unit
) {
    if (isOpen) {
        AlertDialog(
            modifier = modifier,
            title = { Text(text = title) },
            text = {
                Text(text = "Are you sure, you want to submit the quiz?")
            },
            onDismissRequest = onDialogDismiss,
            confirmButton = {
                TextButton(onClick = onConfirmButtonClick) {
                    Text(text = confirmButtonText)
                }
            },
            dismissButton = {
                TextButton(onClick = onDialogDismiss) {
                    Text(text = dismissButtonText)
                }
            },
        )
    }
}

@Preview
@Composable
private fun PreviewSubmitQuizDialog() {
    SubmitQuizDialog(
        isOpen = true,
        onDialogDismiss = {},
        onConfirmButtonClick = {}
    )
}