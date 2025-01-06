package com.test.kmp.todo.app.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.kmp.todo.app.data.TasksRepository
import com.test.kmp.todo.app.data.models.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

sealed interface AddTaskEffect {
    data object NavigateBack : AddTaskEffect
}

data class AddTaskState(
    val uiState: AddTaskUiState = AddTaskUiState(),
    val effect: AddTaskEffect? = null
)

class AddTaskViewModel(
    private val repository: TasksRepository
) : ViewModel() {

    private val _state = MutableStateFlow(AddTaskState())
    val state = _state.asStateFlow()

    fun titleTextChanged(titleText: String) {
        _state.update { currentState ->
            currentState.copy(
                uiState = currentState.uiState.copy(
                    titleText = titleText,
                    addIsAvailable = titleText.isNotBlank() && currentState.uiState.descriptionText.isNotBlank()
                )
            )
        }
    }

    fun descriptionTextChanged(descriptionText: String) {
        _state.update { currentState ->
            currentState.copy(
                uiState = currentState.uiState.copy(
                    descriptionText = descriptionText,
                    addIsAvailable = currentState.uiState.titleText.isNotBlank() && descriptionText.isNotBlank()
                )
            )
        }
    }

    @OptIn(ExperimentalUuidApi::class)
    fun addTapped() {
        val taskData = Task(
            id = Uuid.random().toHexString(),
            name = _state.value.uiState.titleText,
            description = _state.value.uiState.descriptionText
        )
        // Todo if syncronus - first set loading and only then back
        repository.addTask(taskData)
        viewModelScope.launch {
            _state.update { currentState ->
                currentState.copy(
                    uiState = currentState.uiState.copy(
                        isAddLoad = true
                    ),
                    effect = AddTaskEffect.NavigateBack
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
