package com.test.kmp.todo.app.finished

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.test.kmp.todo.app.destinations.DirectDestinations
import com.test.kmp.todo.app.destinations.FinishedDestinations
import com.test.kmp.todo.app.home.TasksListEffect
import com.test.kmp.todo.app.ui.TaskListScreen
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.finishedTasksNavigation(
    navController: NavHostController,
) {
    navigation<FinishedDestinations.FinishedTasksRoute>(
        startDestination = FinishedDestinations.FinishedTasks
    ) {
        composable<FinishedDestinations.FinishedTasks> {
            val viewModel = koinViewModel<FinishedTasksListViewModel>()
            val uiState by viewModel.uiState.collectAsState()

            fun TasksListEffect.handle() {
                when (this) {
                    is TasksListEffect.OpenTaskDetails -> navController.navigate(
                        DirectDestinations.TaskDetails(
                            taskId
                        )
                    )
                }
                viewModel.effectHandled()
            }

            LaunchedEffect(uiState.effect) {
                uiState.effect?.handle()
            }

            TaskListScreen(
                uiState = uiState,
                markAsDoneClick = null,
                revokeTaskClick = viewModel::revokeTask,
                taskCLick = viewModel::taskTapped,
            )
        }
    }
}
