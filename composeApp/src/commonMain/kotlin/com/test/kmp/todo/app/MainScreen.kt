package com.test.kmp.todo.app

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.test.kmp.todo.app.add.AddTaskScreenEntry
import com.test.kmp.todo.app.destinations.DirectDestinations
import com.test.kmp.todo.app.destinations.HomeDestinations
import com.test.kmp.todo.app.details.TaskDetailsScreenEntry
import com.test.kmp.todo.app.edit.EditTaskScreenEntry
import com.test.kmp.todo.app.emojis.emojisNavigation
import com.test.kmp.todo.app.finished.finishedTasksNavigation
import com.test.kmp.todo.app.home.taskNavigation
import com.test.kmp.todo.app.ui.BottomNavBar
import com.test.kmp.todo.app.ui.navigationButtonsList


@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val possibleRoutes = navigationButtonsList.map { it.route }
    val mainRoutesVisible = possibleRoutes.any { possibleRoute ->
        currentDestination?.hierarchy?.any { destination ->
            destination.hasRoute(possibleRoute::class) } == true
    }

    Scaffold(
        floatingActionButton = {
            AnimatedVisibility(
                visible = mainRoutesVisible
            ) {
                FloatingActionButton(
                    onClick = {
                        navController.navigate(DirectDestinations.AddTask)
                    },
                    content = {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null
                        )
                    }
                )
            }
        },
        //.windowInsetsPadding(WindowInsets.safeDrawing)
        modifier = modifier,
        bottomBar = {
            BottomNavBar(
                modifier = Modifier.fillMaxWidth(),
                navController = navController,
                mainRoutesVisible = mainRoutesVisible
            )
        }
    ) {
        ContentContainer(
            modifier = Modifier.fillMaxSize().padding(it),
            navController = navController
        )
    }
}

@Composable
fun ContentContainer(
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
        emojisNavigation(navController)
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
    }
}
