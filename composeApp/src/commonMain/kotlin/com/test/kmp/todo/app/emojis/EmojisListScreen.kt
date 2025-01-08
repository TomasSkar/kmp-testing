package com.test.kmp.todo.app.emojis

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.test.kmp.todo.app.data.models.Emoji
import io.ktor.http.ContentType.Text.Html

sealed interface EmojisListUiState {
    data object Loading: EmojisListUiState
    data object Error: EmojisListUiState
    data class Loaded(val emojiList: List<Emoji>): EmojisListUiState
}

@Composable
fun EmojisListScreen(
    uiState: EmojisListUiState,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ){
        AnimatedContent(
            modifier = modifier,
            targetState = uiState
        ){ state ->
            when(state){
                EmojisListUiState.Loading -> {
                    Box(Modifier.fillMaxSize()){
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                }
                EmojisListUiState.Error -> {
                    Box(Modifier.fillMaxSize()){
                        Text(
                            modifier = Modifier.align(Alignment.Center).padding(24.dp),
                            text = "Error happened",
                            style = MaterialTheme.typography.displaySmall
                        )
                    }
                }
                is EmojisListUiState.Loaded -> {
                    if(state.emojiList.isEmpty()){
                        Box(Modifier.fillMaxSize()){
                            Text(
                                modifier = Modifier.align(Alignment.Center).padding(24.dp),
                                text = "No emojis found",
                                style = MaterialTheme.typography.displaySmall
                            )
                        }
                    } else {
                        LazyColumn(
                            contentPadding = PaddingValues(24.dp)
                        ) {
                            items(state.emojiList){ emojiData ->
                                UnicodeText(
                                    unicode = emojiData.unicode.first(),
                                    modifier = Modifier.size(48.dp),
                                )
                                Text(
                                    text = emojiData.unicode.first(),
                                    modifier = Modifier.size(48.dp),
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

fun parseUnicode(unicode: String): String {
    return unicode.split("U+")
        .last()
        .toInt(16)
        .toChar()
        .toString()
}

@Composable
fun UnicodeText(
    unicode: String,
    modifier: Modifier
) {
    val emoji = unicode.split("U+").last().toInt(16).toChar().toString()
    Text(
        text = emoji,
        modifier = modifier,
        fontFamily = FontFamily.Default // Use a custom font if needed

    )
}