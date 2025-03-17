package com.synac.quiztime.presentation.dashboard.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun NameEditDialog(
    modifier: Modifier = Modifier,
    isOpen: Boolean,
    textFieldValue: String,
    usernameError: String?,
    title: String = "Edit you name",
    confirmButtonText: String = "Rename",
    dismissButtonText: String = "Cancel",
    onTextFieldValueChange: (String) -> Unit,
    onDialogDismiss: () -> Unit,
    onConfirmButtonClick: () -> Unit
) {
    if (isOpen) {
        AlertDialog(
            modifier = modifier,
            title = { Text(text = title) },
            text = {
                OutlinedTextField(
                    value = textFieldValue,
                    onValueChange = onTextFieldValueChange,
                    singleLine = true,
                    isError = usernameError != null && textFieldValue.isNotBlank(),
                    supportingText = { Text(text = usernameError.orEmpty()) }
                )
            },
            onDismissRequest = onDialogDismiss,
            confirmButton = {
                TextButton(
                    onClick = onConfirmButtonClick,
                    enabled = usernameError == null
                ) {
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
private fun PreviewNameEditDialog() {
    NameEditDialog(
        isOpen = true,
        textFieldValue = "a",
        usernameError = "Name is too long",
        onDialogDismiss = {},
        onConfirmButtonClick = {},
        onTextFieldValueChange = {}
    )
}