package com.test.kmp.todo.app.games.lookup

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.toRoute
import com.test.kmp.todo.app.destinations.DirectDestinations
import com.test.kmp.todo.app.destinations.GamesDestinations
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun GamesLookupScreenEntry(
    navController: NavHostController,
    backStackEntry: NavBackStackEntry,
) {
    val args = backStackEntry.toRoute<GamesDestinations.GamesDealLookup>()
    val viewModel = koinViewModel<GamesLookupViewModel>(parameters = { parametersOf(args.dealId) })
    val state by viewModel.state.collectAsState()

    GameLookupScreen(
        uiState = state,
        backClick = {
            navController.popBackStack()
        }
    )
}