package com.synac.quiztime.presentation.common_component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    errorMessage: String,
    onRefreshIconClick: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        IconButton(
            onClick = onRefreshIconClick
        ) {
            Icon(
                modifier = Modifier.size(100.dp),
                imageVector = Icons.Default.Refresh,
                contentDescription = "Refresh"
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = errorMessage,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewErrorScreen() {
    ErrorScreen(
        modifier = Modifier.fillMaxSize(),
        errorMessage = "Something went wrong.",
        onRefreshIconClick = {}
    )
}