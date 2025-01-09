package com.test.kmp.todo.app.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.test.kmp.todo.app.destinations.FinishedDestinations
import com.test.kmp.todo.app.destinations.GamesDestinations
import com.test.kmp.todo.app.destinations.HomeDestinations

data class BottomBarRoute(
    val route: Any,
    val title: String,
    val icon: ImageVector
)

val navigationButtonsList = listOf(
    BottomBarRoute(
        route = HomeDestinations.TasksRoute,
        title = "Active tasks",
        icon = Icons.Default.Create
    ),
    BottomBarRoute(
        route = FinishedDestinations.FinishedTasksRoute,
        title = "Finished tasks",
        icon = Icons.Default.Done
    ),
    BottomBarRoute(
        route = GamesDestinations.GamesRoute,
        title = "Deals",
        icon = Icons.Default.Search
    ),
)

@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier,
    navController: NavController,
    mainRoutesVisible: Boolean,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    AnimatedVisibility(
        visible = mainRoutesVisible,
        enter = slideInVertically(
            initialOffsetY = {
                it / 2
            },
        ),
        exit = slideOutVertically(
            targetOffsetY = {
                it / 2
            },
        ),
        content = {
            NavigationBar(
                modifier = modifier
            ) {
                navigationButtonsList.forEach { bottomBarRoute ->
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any { it.hasRoute(bottomBarRoute.route::class) } == true,
                        onClick = {
                            navController.navigate(bottomBarRoute.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = bottomBarRoute.icon,
                                contentDescription = null
                            )
                        },
                        label = {
                            Text(
                                text = bottomBarRoute.title
                            )
                        }
                    )
                }
            }
        }
    )
}
