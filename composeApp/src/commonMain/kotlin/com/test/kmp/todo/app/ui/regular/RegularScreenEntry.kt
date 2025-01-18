package com.test.kmp.todo.app.ui.regular

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.test.kmp.todo.app.destinations.DirectDestinations
import com.test.kmp.todo.app.destinations.FinishedDestinations
import com.test.kmp.todo.app.destinations.GamesDestinations
import com.test.kmp.todo.app.destinations.HomeDestinations

data class NavigationRoutes(
    val route: Any,
    val title: String,
    val icon: ImageVector
)

val navigationButtonsList = listOf(
    NavigationRoutes(
        route = HomeDestinations.TasksRoute,
        title = "Active tasks",
        icon = Icons.Default.Create
    ),
    NavigationRoutes(
        route = FinishedDestinations.FinishedTasksRoute,
        title = "Finished tasks",
        icon = Icons.Default.Done
    ),
    NavigationRoutes(
        route = GamesDestinations.GamesRoute,
        title = "Deals",
        icon = Icons.Default.Search
    ),
)

@Composable
fun RegularScreenEntry(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val possibleRoutes = navigationButtonsList.map { it.route }
    val mainRoutesVisible = possibleRoutes.any { possibleRoute ->
        currentDestination?.hierarchy?.any { destination ->
            destination.hasRoute(possibleRoute::class)
        } == true
    }


    NavigationSuiteScaffold(
        navigationSuiteColors = NavigationSuiteDefaults.colors(
            navigationBarContainerColor = MaterialTheme.colorScheme.surfaceContainer,
            navigationRailContainerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        navigationSuiteItems = {
            navigationButtonsList.forEach { navButton ->
                item(
                    icon = {
                        Icon(
                            imageVector = navButton.icon,
                            contentDescription = null
                        )
                    },
                    label = { Text(navButton.title) },
                    selected = currentDestination?.hierarchy?.any { it.hasRoute(navButton.route::class) } == true,
                    onClick = {
                        navController.navigate(navButton.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination when
                            // reselecting the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) {
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
        ) {
            ContentContainer(
                modifier = Modifier.fillMaxSize().padding(it),
                navController = navController
            )
        }
    }
}
