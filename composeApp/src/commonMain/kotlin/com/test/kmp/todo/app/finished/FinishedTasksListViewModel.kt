package com.test.kmp.todo.app.finished

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.kmp.todo.app.data.TasksRepository
import com.test.kmp.todo.app.home.TaskListUiState
import com.test.kmp.todo.app.home.TasksListEffect
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FinishedTasksListViewModel(
    private val repository: TasksRepository
) : ViewModel() {

    init {
        viewModelScope.launch {
            delay(1000)
            repository.allTasksFlow.onEach { tasksList ->
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

    fun taskTapped(taskId: String) {
        _uiState.update { currentState ->
            currentState.copy(
                effect = TasksListEffect.OpenTaskDetails(taskId)
            )
        }
    }

    fun revokeTask(taskId: String) {
        repository.revokeTask(taskId = taskId)
    }

    fun effectHandled() {
        _uiState.update { currentState ->
            currentState.copy(
                effect = null
            )
        }
    }
}