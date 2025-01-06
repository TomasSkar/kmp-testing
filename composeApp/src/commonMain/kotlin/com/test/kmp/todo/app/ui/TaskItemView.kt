package com.test.kmp.todo.app.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.test.kmp.todo.app.data.models.Task

@Composable
fun TaskItemView(
    taskData: Task,
    markAsDoneClick: ((taskId: String) -> Unit)?,
    revokeTaskClick: ((taskId: String) -> Unit)?,
    taskClick: (taskId: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.0f),
        onClick = {
            taskClick(taskData.id)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 12.dp,
                    vertical = 8.dp
                )
        ) {
            Text(
                text = taskData.name,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Start
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = taskData.description,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Start
            )
            Spacer(Modifier.height(8.dp))
            if(taskData.isFinished){
                revokeTaskClick?.let {
                    OutlinedButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { revokeTaskClick(taskData.id) },
                        content = {
                            Text(text = "Revoke task")
                        }
                    )
                }
            } else {
                markAsDoneClick?.let {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { markAsDoneClick(taskData.id) },
                        content = {
                            Text(text = "Mark as done")
                        }
                    )
                }
            }
        }
    }
}
