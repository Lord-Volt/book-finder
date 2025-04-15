package com.example.bookfinder.book.domain

data class BookDetail (
    val title: String,
    val authors: List<String>,
    val publisher: String?,
    val publishedDate: String,
    val description: String,
    val pageCount: Int?,
    val categories: List<String>?,
    val imageLink: String
)