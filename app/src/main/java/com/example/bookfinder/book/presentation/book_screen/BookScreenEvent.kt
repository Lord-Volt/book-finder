package com.example.bookfinder.book.presentation.book_screen

import com.example.bookfinder.core.domain.util.NetworkError

sealed interface BookScreenEvent {
    data class Error(val error: NetworkError): BookScreenEvent
}