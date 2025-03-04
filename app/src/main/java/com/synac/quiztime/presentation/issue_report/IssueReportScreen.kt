package com.synac.quiztime.presentation.issue_report

import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import com.synac.quiztime.presentation.issue_report.component.IssueReportScreenTopBar
import com.synac.quiztime.presentation.issue_report.component.QuestionCard

@Composable
fun IssueReportScreen(
    state: IssueReportState,
    onBackButtonClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        IssueReportScreenTopBar(
            title = "Report an Issue",
            onBackButtonClick = onBackButtonClick
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(10.dp)
                .verticalScroll(rememberScrollState())
        ) {
            QuestionCard(
                modifier = Modifier.fillMaxWidth(),
                question = state.quizQuestion,
                isCardExpanded = state.isQuestionCardExpanded,
                onExpandClick = {}
            )
            Spacer(modifier = Modifier.height(10.dp))
            IssueTypeSection(
                selectedIssueType = state.issueType,
                otherIssueText = state.otherIssueText,
                onOtherIssueTextChange = {},
                onIssueTypeClick = {}
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                value = state.additionComment,
                onValueChange = {},
                label = { Text(text = "Additional Comment") },
                supportingText = {
                    Text(text = "Describe the issue in more detail (Optional)")
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.emailForFollowUp,
                onValueChange = {},
                label = { Text(text = "Email for Follow-up") },
                singleLine = true,
                supportingText = { Text(text = "(Optional)") }
            )
        }
        Button(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(10.dp),
            onClick = {}
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 10.dp),
                text = "Submit Report"
            )
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun IssueTypeSection(
    modifier: Modifier = Modifier,
    selectedIssueType: IssueType,
    otherIssueText: String,
    onOtherIssueTextChange: (String) -> Unit,
    onIssueTypeClick: (IssueType) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "ISSUE TYPE",
            style = MaterialTheme.typography.bodySmall
        )
        FlowRow {
            IssueType.entries.forEach { issue ->
                Row(
                    modifier = Modifier
                        .widthIn(250.dp)
                        .clickable { onIssueTypeClick(issue) },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = issue == selectedIssueType,
                        onClick = { onIssueTypeClick(issue) }
                    )
                    if (issue == IssueType.OTHER) {
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = otherIssueText,
                            onValueChange = onOtherIssueTextChange,
                            label = { Text(text = issue.text) },
                            singleLine = true,
                            enabled = selectedIssueType == IssueType.OTHER
                        )
                    } else {
                        Text(
                            text = issue.text
                        )
                    }
                }
            }
        }
    }
}

@PreviewScreenSizes
//@Preview(showBackground = true)
@Composable
private fun PreviewIssueReportScreen() {
    IssueReportScreen(
        state = IssueReportState(),
        onBackButtonClick = {}
    )
}