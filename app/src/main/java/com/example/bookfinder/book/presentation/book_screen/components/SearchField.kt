package com.example.bookfinder.book.presentation.book_screen.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bookfinder.ui.theme.BookFinderTheme

@Composable
fun SearchField(
    query: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = query,
        onValueChange = { newText ->
            onValueChange(newText)
        },
        placeholder = { Text("Search for books!") },
        singleLine = true,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    )
}

@Preview
@Composable
private fun SearchFieldPreview() {
    BookFinderTheme {
        SearchField(
            query = "",
            onValueChange = {}
        )
    }
}