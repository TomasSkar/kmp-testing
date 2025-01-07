package com.test.kmp.todo.app.edit

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

sealed interface EditTaskEffect {
    data object NavigateBack : EditTaskEffect
}

data class EditTaskState(
    val currentTask: Task = Task(0,"",""),
    val uiState: EditTaskUiState = EditTaskUiState(),
    val effect: EditTaskEffect? = null
)

class  EditTaskViewModel(
    taskId: Long,
    private val getTaskFlowUseCase: GetTaskFlowUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(EditTaskState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getTaskFlowUseCase(taskId).stateIn(viewModelScope).collect { task ->
                if(task != null) {
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
            }
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
        viewModelScope.launch {
            updateTaskUseCase(
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
    }

    fun effectHandled() {
        _state.update { currentState ->
            currentState.copy(
                effect = null
            )
        }
    }
}
