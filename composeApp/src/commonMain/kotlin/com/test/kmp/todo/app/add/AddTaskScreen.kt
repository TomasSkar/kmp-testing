package com.test.kmp.todo.app.add

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.test.kmp.todo.app.ui.InlineHeader

data class AddTaskUiState(
    val titleText: String = "",
    val descriptionText: String = "",
    val isAddLoad: Boolean = false,
    val addIsAvailable: Boolean = false,
)

@Composable
fun AddTaskScreen(
    state: AddTaskUiState,
    titleTextChanged: (String) -> Unit,
    descriptionTextChanged: (String) -> Unit,
    addClick: () -> Unit,
    backClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val textFieldColors = TextFieldDefaults.colors(
        unfocusedIndicatorColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
    )

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = modifier.fillMaxWidth(),
        ) {
            InlineHeader(
                title = "Add new task",
                backClick = backClick,
                modifier = Modifier.padding(horizontal = 24.dp)
            )
            HorizontalDivider()
            Spacer(Modifier.height(12.dp))
            Column(
                modifier = Modifier.padding(horizontal = 24.dp)
            ) {
                TextField(
                    value = state.titleText,
                    onValueChange = titleTextChanged,
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = "Task title")
                    },
                    colors = textFieldColors,
                )
                Spacer(Modifier.height(12.dp))
                TextField(
                    value = state.descriptionText,
                    onValueChange = descriptionTextChanged,
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = "Task description")
                    },
                    colors = textFieldColors,
                )
                Spacer(Modifier.height(24.dp))
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !state.isAddLoad && state.addIsAvailable,
                    onClick = addClick,
                ) {
                    Text(text = "Add new task")
                    AnimatedVisibility(state.isAddLoad) {
                        Row {
                            Spacer(Modifier.width(8.dp))
                            CircularProgressIndicator(modifier = Modifier.size(14.dp))
                        }
                    }
                }
            }
        }
    }
}
