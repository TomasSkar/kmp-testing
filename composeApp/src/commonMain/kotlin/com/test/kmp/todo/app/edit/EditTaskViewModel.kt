package com.test.kmp.todo.app.edit

import androidx.lifecycle.ViewModel
import com.test.kmp.todo.app.data.TasksRepository
import com.test.kmp.todo.app.data.models.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

sealed interface EditTaskEffect {
    data object NavigateBack : EditTaskEffect
}

data class EditTaskState(
    val currentTask: Task = Task("","",""),
    val uiState: EditTaskUiState = EditTaskUiState(),
    val effect: EditTaskEffect? = null
)

class  EditTaskViewModel(
    taskId: String,
    private val repository: TasksRepository
) : ViewModel() {

    private val _state = MutableStateFlow(EditTaskState())
    val state = _state.asStateFlow()

    init {
        val task = repository.getTask(taskId)
        _state.update { currentState ->
            currentState.copy(
                currentTask = task,
                uiState = currentState.uiState.copy(
                    titleText = task.name,
                    descriptionText = task.description,
                    editIsAvailable = false
                )
            )
        }
    }

    fun titleTextChanged(titleText: String) {
        _state.update { currentState ->
            currentState.copy(
                uiState = currentState.uiState.copy(
                    titleText = titleText,
                    editIsAvailable = titleText.isNotBlank() && currentState.uiState.descriptionText.isNotBlank()
                )
            )
        }
    }

    fun descriptionTextChanged(descriptionText: String) {
        _state.update { currentState ->
            currentState.copy(
                uiState = currentState.uiState.copy(
                    descriptionText = descriptionText,
                    editIsAvailable = currentState.uiState.titleText.isNotBlank() && descriptionText.isNotBlank()
                )
            )
        }
    }

    fun editTapped() {
        repository.updateTask(
            _state.value.currentTask.copy(
                name = _state.value.uiState.titleText,
                description = _state.value.uiState.descriptionText
            )
        )
        _state.update { currentState ->
            currentState.copy(
                effect = EditTaskEffect.NavigateBack
            )
        }
    }

    fun effectHandled() {
        _state.update { currentState ->
            currentState.copy(
                effect = null
            )
        }
    }
}
