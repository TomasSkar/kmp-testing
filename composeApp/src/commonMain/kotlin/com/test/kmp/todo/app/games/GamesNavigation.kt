package com.test.kmp.todo.app.games

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.test.kmp.todo.app.destinations.GamesDestinations
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.gamesNavigation(
    navController: NavHostController,
) {
    navigation<GamesDestinations.GamesRoute>(
        startDestination = GamesDestinations.GamesDeals
    ) {
        composable<GamesDestinations.GamesDeals> {
            val viewModel = koinViewModel<GamesViewModel>()
            val state by viewModel.state.collectAsState()

            GameDealsScreen(
                uiState = state,
                onDealClick = {
                    navController.navigate(GamesDestinations.GamesDealLookup(it))
                }
            )
        }
    }
}