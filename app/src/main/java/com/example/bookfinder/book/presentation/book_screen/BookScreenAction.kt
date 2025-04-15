package com.example.bookfinder.book.presentation.book_screen

import com.example.bookfinder.book.presentation.model.BookUi

sealed interface BookScreenAction {
    data class OnSearchTextChange(val searchText: String): BookScreenAction
    data class OnBookClick(val bookUi: BookUi): BookScreenAction
    data object OnDescriptionChanged : BookScreenAction
}