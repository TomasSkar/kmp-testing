package com.test.kmp.todo.app.details

import com.test.kmp.todo.app.domain.GetTaskFlowUseCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.kmp.todo.app.domain.UpdateTaskUseCase
import com.test.kmp.todo.app.data.models.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed interface TaskDetailsEffect {
    data class OpenTaskEdit(val taskId: Long) : TaskDetailsEffect
    data object NavigateBack : TaskDetailsEffect
}

data class TaskDetailsState(
    val selectedTask: Task = Task(0, "", ""),
    val uiState: TaskDetailsUiState = TaskDetailsUiState(),
    val effect: TaskDetailsEffect? = null
)

class TaskDetailsViewModel(
    private val getTaskFlowUseCase: GetTaskFlowUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(TaskDetailsState())
    val state = _state.asStateFlow()

    fun incomingTaskUpdated(taskId: Long) {
        viewModelScope.launch {
            getTaskFlowUseCase(taskId).stateIn(viewModelScope).collect { task ->
                if (task != null) {
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
            }
        }
    }

    fun finishTaskTapped() {
        viewModelScope.launch {
            updateTaskUseCase(
                _state.value.selectedTask.copy(
                    isFinished = true
                )
            )
            _state.update { currentState ->
                currentState.copy(
                    effect = TaskDetailsEffect.NavigateBack
                )
            }
        }
    }

    fun revokeTaskTapped() {
        viewModelScope.launch {
            updateTaskUseCase(
                _state.value.selectedTask.copy(
                    isFinished = false
                )
            )
            _state.update { currentState ->
                currentState.copy(
                    effect = TaskDetailsEffect.NavigateBack
                )
            }
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