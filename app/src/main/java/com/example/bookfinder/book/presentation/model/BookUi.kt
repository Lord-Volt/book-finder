package com.example.bookfinder.book.presentation.model

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.bookfinder.book.domain.Book
import com.example.bookfinder.book.domain.BookDetail
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Locale

data class BookUi(
    val id: String,
    val imageLink: String?,
    val title: String,
    val authors: List<String> = emptyList(),
    val publisher: String? = null,
    val publishedDate: DisplayableDate? = null,
    val description: String,
    val pageCount: Int? = null,
    val categories: List<String>? = null,
    val largeImageLink: String? = null,
)

data class DisplayableDate(
    val value: String,
    val formatted: String
)

fun Book.toBookUi(): BookUi {
    return BookUi(
        imageLink = imageLink,
        title = title,
        id = id,
        description = ""
    )
}

fun BookDetail.toBookUi(): BookUi {
    return BookUi(
        id = "",
        imageLink = "",
        title = title,
        authors = authors,
        publisher = publisher,
        publishedDate = publishedDate.toDisplayableDate(),
        description = description,
        pageCount = pageCount,
        categories = categories,
        largeImageLink = imageLink
    )
}

fun String.toDisplayableDate(): DisplayableDate {
    return try {
        val parsedDate = LocalDate.parse(this, DateTimeFormatter.ISO_DATE_TIME)
        val outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.getDefault())
        DisplayableDate(
            value = this,
            formatted = parsedDate.format(outputFormat)
        )
    } catch (e: DateTimeParseException) {
        DisplayableDate(
            value = this,
            formatted = this
        )
    }
}
