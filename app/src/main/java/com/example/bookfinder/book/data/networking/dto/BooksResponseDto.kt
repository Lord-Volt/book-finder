package com.example.bookfinder.book.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class BooksResponseDto(
    val totalItems: Int,
    val items: List<BookDto> = emptyList()
)
