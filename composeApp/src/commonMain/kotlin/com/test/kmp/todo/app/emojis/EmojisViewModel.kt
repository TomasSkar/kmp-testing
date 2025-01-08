package com.test.kmp.todo.app.emojis

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EmojisViewModel(
    private val getAllEmojisUseCase: GetAllEmojisUseCase
): ViewModel() {
    private val _state = MutableStateFlow<EmojisListUiState>(EmojisListUiState.Loading)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getAllEmojisUseCase().onSuccess {
                _state.value = EmojisListUiState.Loaded(it)
            }.onFailure {
                _state.value = EmojisListUiState.Error
            }
        }
    }
}