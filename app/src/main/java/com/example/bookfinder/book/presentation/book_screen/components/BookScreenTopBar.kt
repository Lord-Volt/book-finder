package com.example.bookfinder.book.presentation.book_screen.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookScreenTopBar(
    query: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            SearchField(
                query = query,
                onValueChange = onValueChange,
                modifier = modifier
            )
        }
    )
}