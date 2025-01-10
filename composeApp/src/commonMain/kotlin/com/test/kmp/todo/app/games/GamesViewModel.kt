package com.test.kmp.todo.app.games

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.kmp.todo.app.games.domain.GetAllDealsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GamesViewModel(
    private val getAllDealsUseCase: GetAllDealsUseCase
): ViewModel() {
    private val _state = MutableStateFlow<GameDealsUiState>(GameDealsUiState.Loading)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getAllDealsUseCase().onSuccess {
                _state.value = GameDealsUiState.Loaded(it)
            }.onFailure {
                _state.value = GameDealsUiState.Error(it.message)
            }
        }
    }
}