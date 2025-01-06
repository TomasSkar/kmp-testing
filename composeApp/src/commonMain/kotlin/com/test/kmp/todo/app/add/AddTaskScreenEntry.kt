package com.test.kmp.todo.app.add

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AddTaskScreenEntry(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val viewModel = koinViewModel<AddTaskViewModel>()
    val state by viewModel.state.collectAsState()

    fun AddTaskEffect.handleEffect() {
        when(this){
            AddTaskEffect.NavigateBack -> navController.popBackStack()
        }
        viewModel.effectHandled()
    }

    LaunchedEffect(state.effect){
        state.effect?.handleEffect()
    }

    AddTaskScreen(
        state = state.uiState,
        titleTextChanged = viewModel::titleTextChanged,
        descriptionTextChanged = viewModel::descriptionTextChanged,
        addClick = viewModel::addTapped,
        backClick = {
            navController.popBackStack()
        },
        modifier = modifier
    )
}