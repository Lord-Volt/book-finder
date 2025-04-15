package com.example.bookfinder.book.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class BookDetailDto(
    val volumeInfo: VolumeInfo
) {
    @Serializable
    data class VolumeInfo(
        val title: String,
        val authors: List<String>,
        val publisher: String,
        val publishedDate: String,
        val description: String = "No description found.",
        val pageCount: Int?,
        val categories: List<String>? = emptyList(),
        val imageLinks: ImageLinks
    ) {
        @Serializable
        data class ImageLinks(
            val large: String
        )
    }
}
