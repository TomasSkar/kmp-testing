package com.test.kmp.todo.app.games.lookup

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.test.kmp.todo.app.data.models.GameDealLookupResponse
import com.test.kmp.todo.app.ui.InlineHeader
import com.test.kmp.todo.app.ui.RowText
import com.test.kmp.todo.app.utils.convertTimestampToHumanReadable

sealed interface GameLookupUiState {
    data object Loading : GameLookupUiState
    data class Error(val message: String?) : GameLookupUiState
    data class Loaded(
        val title: String,
        val lookupData: GameDealLookupResponse
    ) : GameLookupUiState
}

@Composable
fun GameLookupScreen(
    uiState: GameLookupUiState,
    modifier: Modifier = Modifier,
    backClick: () -> Unit,
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            InlineHeader(
                title = (uiState as? GameLookupUiState.Loaded)?.title ?: "",
                backClick = backClick
            )
            AnimatedContent(
                modifier = modifier,
                targetState = uiState
            ) { state ->
                when (state) {
                    GameLookupUiState.Loading -> {
                        Box(Modifier.fillMaxSize()) {
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                        }
                    }

                    is GameLookupUiState.Error -> {
                        Box(Modifier.fillMaxSize()) {
                            Text(
                                modifier = Modifier.align(Alignment.Center).padding(24.dp),
                                text = state.message ?: "Something went wrong",
                                style = MaterialTheme.typography.displaySmall
                            )
                        }
                    }

                    is GameLookupUiState.Loaded -> {
                        Column(
                            modifier = Modifier.fillMaxWidth().padding(12.dp)
                        ) {
                            AsyncImage(
                                model = state.lookupData.gameInfo.thumb,
                                contentDescription = null,
                                modifier = Modifier.fillMaxWidth().heightIn(max = 120.dp),
                                contentScale = ContentScale.Fit
                            )
                            Spacer(Modifier.height(8.dp))
                            Text(
                                text = state.lookupData.gameInfo.name,
                                modifier = Modifier.fillMaxWidth(),
                                style = MaterialTheme.typography.titleMedium
                            )
                            Spacer(Modifier.height(8.dp))
                            RowText(
                                title = "Publisher name:",
                                value = state.lookupData.gameInfo.publisher ?: "Unknown"
                            )
                            HorizontalDivider(modifier = Modifier.fillMaxWidth())
                            if(state.lookupData.gameInfo.releaseDate > 0){
                                Spacer(Modifier.height(8.dp))
                                RowText(
                                    title = "Release date:",
                                    value = convertTimestampToHumanReadable(state.lookupData.gameInfo.releaseDate)
                                )
                                HorizontalDivider(modifier = Modifier.fillMaxWidth())
                            }
                            Text(
                                text = "Cheapest time:",
                                modifier = Modifier.fillMaxWidth(),
                                style = MaterialTheme.typography.titleMedium
                            )
                            Spacer(Modifier.height(8.dp))
                            RowText(
                                title = "Price:",
                                value = state.lookupData.cheapestPrice?.price ?: "-"
                            )
                            if((state.lookupData.cheapestPrice?.date ?: 0 )> 0) {
                                Spacer(Modifier.height(8.dp))
                                RowText(
                                    title = "On date:",
                                    value = convertTimestampToHumanReadable(state.lookupData.cheapestPrice?.date ?: 0)
                                )
                            }
                            HorizontalDivider(modifier = Modifier.fillMaxWidth())

                            LazyColumn(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                item {
                                    Text(
                                        text = "Stores deals",
                                        modifier = Modifier.fillMaxWidth(),
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                }
                                if(state.lookupData.cheaperStores.isEmpty()){
                                    item {
                                        Text(
                                            text = "Not found",
                                            modifier = Modifier.fillMaxWidth(),
                                            style = MaterialTheme.typography.titleMedium
                                        )
                                    }
                                }
                                items(state.lookupData.cheaperStores){ store ->
                                    Spacer(Modifier.height(4.dp))
                                    RowText(
                                        title = "Price: ",
                                        value = store.salePrice
                                    )
                                    Spacer(Modifier.height(4.dp))
                                    RowText(
                                        title = "Deal ID: ",
                                        value = store.dealID
                                    )
                                    HorizontalDivider(modifier = Modifier.fillMaxWidth())
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
