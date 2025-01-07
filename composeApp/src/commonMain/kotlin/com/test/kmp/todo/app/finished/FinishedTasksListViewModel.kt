package com.test.kmp.todo.app.finished

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.kmp.todo.app.domain.GetTasksFlowUseCase
import com.test.kmp.todo.app.domain.UpdateTaskUseCase
import com.test.kmp.todo.app.home.TaskListUiState
import com.test.kmp.todo.app.home.TasksListEffect
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FinishedTasksListViewModel(
    private val getTasksFlowUseCase: GetTasksFlowUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
) : ViewModel() {

    init {
        viewModelScope.launch {
            getTasksFlowUseCase().onEach { tasksList ->
                val newTasks = tasksList.filter { task -> task.isFinished }
                _uiState.update { currentState ->
                    currentState.copy(
                        taskList = newTasks,
                        isLoading = false
                    )
                }
            }.stateIn(viewModelScope)
        }
    }

    private val _uiState = MutableStateFlow(TaskListUiState())
    val uiState = _uiState.asStateFlow()

    fun taskTapped(taskId: Long) {
        _uiState.update { currentState ->
            currentState.copy(
                effect = TasksListEffect.OpenTaskDetails(taskId)
            )
        }
    }

    fun revokeTask(taskId: Long) {
        val updateTask = _uiState.value.taskList.first { it.id == taskId }
        viewModelScope.launch {
            updateTaskUseCase(updateTask.copy(isFinished = false))
        }
    }

    fun effectHandled() {
        _uiState.update { currentState ->
            currentState.copy(
                effect = null
            )
        }
    }
}