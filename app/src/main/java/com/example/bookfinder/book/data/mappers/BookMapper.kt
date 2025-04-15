package com.example.bookfinder.book.data.mappers

import com.example.bookfinder.book.data.networking.dto.BookDetailDto
import com.example.bookfinder.book.data.networking.dto.BookDto
import com.example.bookfinder.book.domain.Book
import com.example.bookfinder.book.domain.BookDetail

fun BookDto.toBook(): Book {
    return Book(
        id = id,
        imageLink = volumeInfo.imageLinks?.thumbnail,
        title = volumeInfo.title
    )
}

fun BookDetailDto.toBookDetail(): BookDetail {
    return BookDetail(
        title = volumeInfo.title,
        authors = volumeInfo.authors,
        publisher = volumeInfo.publisher,
        publishedDate = volumeInfo.publishedDate,
        description = volumeInfo.description,
        pageCount = volumeInfo.pageCount,
        categories = volumeInfo.categories,
        imageLink = volumeInfo.imageLinks.large
    )
}