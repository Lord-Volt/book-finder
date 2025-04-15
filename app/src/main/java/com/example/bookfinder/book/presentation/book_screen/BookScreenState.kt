package com.example.bookfinder.book.presentation.book_screen

import androidx.compose.runtime.Immutable
import com.example.bookfinder.book.presentation.model.BookUi

@Immutable
data class BookScreenState(
    val isLoading: Boolean = false,
    val books: List<BookUi> = emptyList(),
    val selectedBook: BookUi = BookUi(
        id = "",
        imageLink = "",
        title = "",
        description = ""
    ),
    val searchFieldText: String? = null,
    val isDescriptionExpanded: Boolean = false
)
