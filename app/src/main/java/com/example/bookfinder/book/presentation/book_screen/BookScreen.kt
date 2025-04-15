package com.example.bookfinder.book.presentation.book_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bookfinder.book.presentation.book_grid.BookGridScreen
import com.example.bookfinder.book.presentation.book_screen.components.BookScreenTopBar
import com.example.bookfinder.book.presentation.model.BookUi

@Composable
fun BookScreen(
    state: BookScreenState,
    onSearchTextChange: (BookScreenAction) -> Unit,
    onBookClick: (BookScreenAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            BookScreenTopBar(
                query = state.searchFieldText ?: "",
                onValueChange = { newText ->
                    onSearchTextChange(BookScreenAction.OnSearchTextChange(newText))
                }
            ) },
        bottomBar = { } // Do later maybe add favorites tab?
    ) {  paddingValues ->
        when {
            state.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            state.books.isEmpty() -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Search for some books!",
                        style = MaterialTheme.typography.displayMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
            else -> {
                BookGridScreen(
                    state = state,
                    onBookClick = { bookUi ->
                        onBookClick(BookScreenAction.OnBookClick(bookUi))
                    },
                    modifier = Modifier
                        .padding(paddingValues)
                )
            }
        }
    }
}