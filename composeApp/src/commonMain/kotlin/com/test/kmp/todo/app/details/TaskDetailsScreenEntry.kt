package com.test.kmp.todo.app.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.toRoute
import com.test.kmp.todo.app.destinations.DirectDestinations
import com.test.kmp.todo.app.destinations.HomeDestinations
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun TaskDetailsScreenEntry(
    navController: NavHostController,
    backStackEntry: NavBackStackEntry,
    modifier: Modifier = Modifier,
) {
    val args = backStackEntry.toRoute<DirectDestinations.TaskDetails>()
    val viewModel = koinViewModel<TaskDetailsViewModel>()
    val state by viewModel.state.collectAsState()

    fun TaskDetailsEffect.handle() {
        when(this){
            TaskDetailsEffect.NavigateBack -> navController.popBackStack()
            is TaskDetailsEffect.OpenTaskEdit -> navController.navigate(DirectDestinations.EditTask(taskId)) /*{
                popUpTo(route = DirectDestinations.TaskDetails) { inclusive = true }
                launchSingleTop = true
            }*/
        }
        viewModel.effectHandled()
    }

    LaunchedEffect(state.effect){
        state.effect?.handle()
    }

    LaunchedEffect(args){
        viewModel.incomingTaskUpdated(args.taskId)
    }

    TaskDetailsScreen(
        state = state.uiState,
        backClick = { navController.popBackStack() },
        revokeTaskClick = viewModel::revokeTaskTapped,
        finishTaskClick = viewModel::finishTaskTapped,
        editTaskClick = viewModel::editTaskTapped,
        modifier = modifier
    )
}