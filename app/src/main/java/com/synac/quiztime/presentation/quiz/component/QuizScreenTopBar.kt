package com.synac.quiztime.presentation.quiz.component

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreenTopBar(
    modifier: Modifier = Modifier,
    title: String,
    onExitQuizButtonClick: () -> Unit
) {
    TopAppBar(
        windowInsets = WindowInsets(0),
        modifier = modifier,
        title = { Text(text = title) },
        actions = {
            IconButton(onClick = onExitQuizButtonClick) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Exit Quiz"
                )
            }
        }
    )
}

@Preview
@Composable
private fun PreviewQuizScreenTopBar() {
    QuizScreenTopBar(
        title = "Android Quiz",
        onExitQuizButtonClick = {}
    )
}