package com.example.bookfinder.book.domain

import com.example.bookfinder.core.domain.util.NetworkError
import com.example.bookfinder.core.domain.util.Result

interface BookDataSource {
    suspend fun getBooks(query: String): Result<List<Book>, NetworkError>
    suspend fun getBookDetail(query: String): Result<BookDetail, NetworkError>
}