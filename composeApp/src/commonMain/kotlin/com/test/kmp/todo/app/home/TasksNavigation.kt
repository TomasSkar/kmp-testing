package com.test.kmp.todo.app.home

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.test.kmp.todo.app.destinations.DirectDestinations
import com.test.kmp.todo.app.destinations.HomeDestinations
import com.test.kmp.todo.app.ui.TaskListScreen
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.taskNavigation(
    navController: NavHostController,
) {
    navigation<HomeDestinations.TasksRoute>(
        startDestination = HomeDestinations.TaskList
    ) {
        composable<HomeDestinations.TaskList> {
            val viewModel = koinViewModel<TasksListViewModel>()
            val uiState by viewModel.uiState.collectAsState()

            fun TasksListEffect.handle() {
                when(this){
                    is TasksListEffect.OpenTaskDetails -> navController.navigate(
                        DirectDestinations.TaskDetails(
                            taskId
                        )
                    )
                }
                viewModel.effectHandled()
            }

            LaunchedEffect(uiState.effect){
                uiState.effect?.handle()
            }

            TaskListScreen(
                uiState = uiState,
                markAsDoneClick = viewModel::markTaskAsFinished,
                // There must be only current running tasks
                revokeTaskClick = null,
                taskCLick = viewModel::taskTapped
            )
        }
    }
}
