package com.test.kmp.todo.app.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.test.kmp.todo.app.home.TaskListUiState
import com.test.kmp.todo.app.ui.TaskItemView

private enum class TaskListContentState {
    Loading, Loaded, Empty
}

@Composable
fun TaskListScreen(
    uiState: TaskListUiState,
    markAsDoneClick: ((taskId: String) -> Unit)?,
    revokeTaskClick: ((taskId: String) -> Unit)?,
    taskCLick: (taskId: String) -> Unit,
    modifier: Modifier = Modifier
) {
    val contentState = when {
        uiState.isLoading -> TaskListContentState.Loading
        uiState.taskList.isNotEmpty() -> TaskListContentState.Loaded
        else -> TaskListContentState.Empty
    }

    Surface(
        modifier = modifier.fillMaxSize()
    ) {
        AnimatedContent(
            targetState = contentState
        ) {
            when(it) {
                TaskListContentState.Loading -> {
                    Box(Modifier.fillMaxSize()){
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                }
                TaskListContentState.Empty -> {
                    val text = if(markAsDoneClick != null){
                        "Currently no tasks added, try to add task"
                    } else {
                        "No finished task right now."
                    }
                    Box(Modifier.fillMaxSize()){
                        Text(
                            modifier = Modifier.align(Alignment.Center).padding(24.dp),
                            text = text,
                            style = MaterialTheme.typography.displaySmall
                        )
                    }
                }
                TaskListContentState.Loaded -> {
                    LazyColumn(
                        contentPadding = PaddingValues(
                            horizontal = 12.dp,
                            vertical = 8.dp
                        )
                    ) {
                        items(uiState.taskList){ task ->
                            TaskItemView(
                                modifier = Modifier.animateItem(),
                                taskData = task,
                                markAsDoneClick = markAsDoneClick,
                                taskClick = taskCLick,
                                revokeTaskClick = revokeTaskClick
                            )
                            Spacer(Modifier.height(8.dp))
                        }
                    }
                }
            }
        }
    }
}