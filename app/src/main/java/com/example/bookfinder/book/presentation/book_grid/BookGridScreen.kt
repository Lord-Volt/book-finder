package com.example.bookfinder.book.presentation.book_grid

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.bookfinder.book.presentation.book_grid.components.BookGridCard
import com.example.bookfinder.book.presentation.book_grid.components.previewBook
import com.example.bookfinder.book.presentation.book_screen.BookScreenState
import com.example.bookfinder.book.presentation.model.BookUi
import com.example.bookfinder.ui.theme.BookFinderTheme

@Composable
fun BookGridScreen(
    state: BookScreenState,
    onBookClick: (BookUi) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        modifier = modifier.padding(horizontal = 8.dp),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(state.books) { bookUi ->
            BookGridCard(
                bookUi = bookUi,
                onClick = { onBookClick(bookUi) },
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

@PreviewLightDark
@Composable
fun BookGridScreenPreview() {
    BookFinderTheme {
        BookGridScreen(
            state = BookScreenState(
                books = (1..50).map {
                    previewBook
                }
            ),
            onBookClick = { }
        )
    }
}