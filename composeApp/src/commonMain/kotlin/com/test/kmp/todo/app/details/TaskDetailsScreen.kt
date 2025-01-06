package com.test.kmp.todo.app.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.test.kmp.todo.app.ui.InlineHeader

data class TaskDetailsUiState(
    val taskTitle: String = "",
    val taskDescription: String = "",
    val isFinished: Boolean = false
)

@Composable
fun TaskDetailsScreen(
    state: TaskDetailsUiState,
    backClick: () -> Unit,
    revokeTaskClick: () -> Unit,
    finishTaskClick: () -> Unit,
    editTaskClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = modifier.fillMaxWidth()
        ) {
            InlineHeader(
                title = "Task details",
                backClick = backClick,
                modifier = Modifier.padding(horizontal = 24.dp)
            )
            Spacer(Modifier.height(14.dp))
            Column(
                modifier = Modifier.padding(horizontal = 24.dp)
            ) {
                Text(
                    text = state.taskTitle,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = state.taskDescription,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(12.dp))
                if(state.isFinished){
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = revokeTaskClick,
                    ) {
                        Text(text = "Revoke task")
                    }
                } else {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = finishTaskClick,
                    ) {
                        Text(text = "Finish task")
                    }
                }
                Spacer(Modifier.height(12.dp))
                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = editTaskClick
                ) {
                    Text(text = "Edit task")
                }
            }
        }
    }
}