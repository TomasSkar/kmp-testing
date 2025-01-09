package com.test.kmp.todo.app.games

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.test.kmp.todo.app.data.models.GameDeal
import com.test.kmp.todo.app.ui.RowText
import com.test.kmp.todo.app.utils.convertTimestampToHumanReadable

sealed interface GameDealsUiState {
    data object Loading : GameDealsUiState
    data class Error(val message: String?) : GameDealsUiState
    data class Loaded(val deals: List<GameDeal>) : GameDealsUiState
}

@Composable
fun GameDealsScreen(
    uiState: GameDealsUiState,
    modifier: Modifier = Modifier,
    onDealClick: (dealId: String) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        AnimatedContent(
            modifier = modifier,
            targetState = uiState
        ) { state ->
            when (state) {
                GameDealsUiState.Loading -> {
                    Box(Modifier.fillMaxSize()) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                }

                is GameDealsUiState.Error -> {
                    Box(Modifier.fillMaxSize()) {
                        Text(
                            modifier = Modifier.align(Alignment.Center).padding(24.dp),
                            text = state.message ?: "Something went wrong",
                            style = MaterialTheme.typography.displaySmall
                        )
                    }
                }

                is GameDealsUiState.Loaded -> {
                    LazyColumn(
                        contentPadding = PaddingValues(12.dp)
                    ) {
                        items(state.deals) { deal ->
                            GameDealComponent(
                                deal = deal,
                                onDealClick = onDealClick
                            )
                            Spacer(Modifier.height(8.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun GameDealComponent(
    deal: GameDeal,
    modifier: Modifier = Modifier,
    onDealClick: (dealId: String) -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = { onDealClick(deal.dealID) }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(
                topStart = 8.dp,
                topEnd = 8.dp
            ))
        ) {
            AsyncImage(
                model = deal.thumb,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().heightIn(max = 120.dp),
                contentScale = ContentScale.Fit
            )
        }
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = deal.title,
                modifier = Modifier.fillMaxSize(),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(Modifier.height(8.dp))
            if(deal.releaseDate > 0) {
                Text(
                    text = convertTimestampToHumanReadable(deal.releaseDate),
                    modifier = Modifier.fillMaxSize(),
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(Modifier.height(8.dp))
            }
            RowText(
                title = "Deal rating:",
                value = deal.dealRating
            )
            HorizontalDivider(modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(4.dp))
            RowText(
                title = "Internal name:",
                value = deal.internalName
            )
            HorizontalDivider(modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(4.dp))
            RowText(
                title = "Steam rating text:",
                value = deal.steamRatingText ?: "-"
            )
            HorizontalDivider(modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(4.dp))
            RowText(
                title = "Normal price:",
                value = deal.normalPrice
            )
            HorizontalDivider(modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(4.dp))
            RowText(
                title = "Sale price:",
                value = deal.salePrice
            )
        }
    }
}
