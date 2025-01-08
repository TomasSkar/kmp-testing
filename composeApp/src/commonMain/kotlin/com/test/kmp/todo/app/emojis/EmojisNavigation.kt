package com.test.kmp.todo.app.emojis

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.test.kmp.todo.app.destinations.EmojisDestinations
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.emojisNavigation(
    navController: NavHostController,
) {
    navigation<EmojisDestinations.EmojisRoute>(
        startDestination = EmojisDestinations.EmojisList
    ) {
        composable<EmojisDestinations.EmojisList> {
            val viewModel = koinViewModel<EmojisViewModel>()
            val state by viewModel.state.collectAsState()

            EmojisListScreen(
                uiState = state
            )
        }
    }
}