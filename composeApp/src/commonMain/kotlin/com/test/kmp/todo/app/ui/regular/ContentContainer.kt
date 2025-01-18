package com.test.kmp.todo.app.ui.regular

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.test.kmp.todo.app.add.AddTaskScreenEntry
import com.test.kmp.todo.app.destinations.DirectDestinations
import com.test.kmp.todo.app.destinations.GamesDestinations
import com.test.kmp.todo.app.destinations.HomeDestinations
import com.test.kmp.todo.app.details.TaskDetailsScreenEntry
import com.test.kmp.todo.app.edit.EditTaskScreenEntry
import com.test.kmp.todo.app.finished.finishedTasksNavigation
import com.test.kmp.todo.app.games.gamesNavigation
import com.test.kmp.todo.app.games.lookup.GamesLookupScreenEntry
import com.test.kmp.todo.app.home.taskNavigation

@Composable
internal fun ContentContainer(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = HomeDestinations.TasksRoute
    ) {
        taskNavigation(navController)
        finishedTasksNavigation(navController)
        gamesNavigation(navController)
        composable<DirectDestinations.AddTask> {
            AddTaskScreenEntry(
                navController = navController
            )
        }
        composable<DirectDestinations.EditTask> { backStackEntry ->
            EditTaskScreenEntry(
                navController = navController,
                backStackEntry = backStackEntry,
            )
        }
        composable<DirectDestinations.TaskDetails> { backStackEntry ->
            TaskDetailsScreenEntry(
                backStackEntry = backStackEntry,
                navController = navController
            )
        }
        composable<GamesDestinations.GamesDealLookup> { backStackEntry ->
            GamesLookupScreenEntry(
                navController = navController,
                backStackEntry = backStackEntry
            )
        }
    }
}