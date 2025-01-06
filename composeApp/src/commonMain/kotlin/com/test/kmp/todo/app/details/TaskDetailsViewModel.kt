package com.test.kmp.todo.app.details

import androidx.lifecycle.ViewModel
import com.test.kmp.todo.app.data.TasksRepository
import com.test.kmp.todo.app.data.models.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

sealed interface TaskDetailsEffect {
    data class OpenTaskEdit(val taskId: String) : TaskDetailsEffect
    data object NavigateBack: TaskDetailsEffect
}

data class TaskDetailsState(
    val selectedTask: Task = Task("","",""),
    val uiState: TaskDetailsUiState = TaskDetailsUiState(),
    val effect: TaskDetailsEffect? = null
)

class TaskDetailsViewModel(
    private val repository: TasksRepository
) : ViewModel() {
    private val _state = MutableStateFlow(TaskDetailsState())
    val state = _state.asStateFlow()

    fun incomingTaskUpdated(taskId: String) {
        val task = repository.getTask(taskId)
        _state.update { currentState ->
            currentState.copy(
                selectedTask = task,
                uiState = currentState.uiState.copy(
                    taskTitle = task.name,
                    taskDescription = task.description,
                    isFinished = task.isFinished
                )
            )
        }
    }

    fun finishTaskTapped() {
        repository.markAsFinished(_state.value.selectedTask.id)
        _state.update { currentState ->
            currentState.copy(
                effect = TaskDetailsEffect.NavigateBack
            )
        }
    }

    fun revokeTaskTapped() {
        repository.revokeTask(_state.value.selectedTask.id)
        _state.update { currentState ->
            currentState.copy(
                effect = TaskDetailsEffect.NavigateBack
            )
        }
    }

    fun editTaskTapped() {
        _state.update { currentState ->
            currentState.copy(
                effect = TaskDetailsEffect.OpenTaskEdit(_state.value.selectedTask.id)
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