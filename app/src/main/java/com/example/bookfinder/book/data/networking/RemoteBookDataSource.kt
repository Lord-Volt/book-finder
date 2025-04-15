package com.example.bookfinder.book.data.networking

import android.util.Log
import com.example.bookfinder.book.data.mappers.toBook
import com.example.bookfinder.book.data.mappers.toBookDetail
import com.example.bookfinder.book.data.networking.dto.BookDetailDto
import com.example.bookfinder.book.data.networking.dto.BooksResponseDto
import com.example.bookfinder.book.domain.Book
import com.example.bookfinder.book.domain.BookDataSource
import com.example.bookfinder.book.domain.BookDetail
import com.example.bookfinder.core.data.networking.safeCall
import com.example.bookfinder.core.domain.util.NetworkError
import com.example.bookfinder.core.domain.util.Result
import com.example.bookfinder.core.data.networking.constructUrl
import com.example.bookfinder.core.domain.util.map
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class RemoteBookDataSource(
    private val httpClient: HttpClient
): BookDataSource {

    override suspend fun getBooks(query: String): Result<List<Book>, NetworkError> {
        val url = "volumes?q=${query}/"
        Log.d("RemoteBookDataSource", "Fetching books with query: $query, URL: $url")
        val result = safeCall<BooksResponseDto> {
            httpClient.get(
                urlString = constructUrl("volumes?q=${query}/") // Fix Later!!!
            )
        }
        return when(result){
            is Result.Error -> Result.Error(result.error)
            is Result.Success -> {
                val totalItems = result.data.totalItems
                if (totalItems == 0) {
                    Result.Error(NetworkError.NO_RESULTS)
                } else {
                    result.map { response ->
                        response.items.map { it.toBook() }
                    }
                }
            }
        }
    }

    override suspend fun getBookDetail(query: String): Result<BookDetail, NetworkError> {
        val url = "volumes/${query}"
        Log.d("RemoteBookDataSource", "Fetching book detail with query: $query, URL: $url")
        return safeCall<BookDetailDto> {
            httpClient.get(
                urlString = constructUrl("volumes/${query}")
            )
        }.map { response ->
            response.toBookDetail()
        }
    }
}