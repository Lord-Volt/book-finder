package com.example.bookfinder.book.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class BookDto(
    val id: String,
    val volumeInfo: VolumeInfo
) {
    @Serializable
    data class VolumeInfo(
        val title: String,
        val imageLinks: ImageLinks?
    ) {
        @Serializable
        data class ImageLinks(
            val thumbnail: String?
        )
    }
}