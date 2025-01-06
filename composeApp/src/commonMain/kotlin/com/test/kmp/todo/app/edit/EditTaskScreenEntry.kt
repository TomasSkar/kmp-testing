package com.test.kmp.todo.app.edit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.toRoute
import com.test.kmp.todo.app.destinations.DirectDestinations
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun EditTaskScreenEntry(
    navController: NavHostController,
    backStackEntry: NavBackStackEntry,
    modifier: Modifier = Modifier
) {
    val args = backStackEntry.toRoute<DirectDestinations.EditTask>()
    val viewModel = koinViewModel<EditTaskViewModel>(parameters = { parametersOf(args.taskId) })
    val state by viewModel.state.collectAsState()

    fun EditTaskEffect.handleEffect() {
        when (this) {
            EditTaskEffect.NavigateBack -> navController.popBackStack()
        }
        viewModel.effectHandled()
    }

    LaunchedEffect(state.effect) {
        state.effect?.handleEffect()
    }

    EditTaskScreen(
        state = state.uiState,
        titleTextChanged = viewModel::titleTextChanged,
        descriptionTextChanged = viewModel::descriptionTextChanged,
        editClick = viewModel::editTapped,
        backClick = {
            navController.popBackStack()
        },
        modifier = modifier
    )
}