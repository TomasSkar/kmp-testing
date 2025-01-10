package com.test.kmp.todo.app.games.lookup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.kmp.todo.app.games.domain.GetGameLookupUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GamesLookupViewModel(
    private val dealId: String,
    private val getGameLookupUseCase: GetGameLookupUseCase
): ViewModel() {
    private val _state = MutableStateFlow<GameLookupUiState>(GameLookupUiState.Loading)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getGameLookupUseCase(dealId).onSuccess {
                _state.value = GameLookupUiState.Loaded(
                    title = it.gameInfo.name,
                    lookupData = it
                )
            }.onFailure {
                _state.value = GameLookupUiState.Error(
                    message = it.message
                )
            }
        }
    }
}