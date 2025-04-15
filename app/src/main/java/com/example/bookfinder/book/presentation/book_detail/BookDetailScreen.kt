package com.example.bookfinder.book.presentation.book_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.bookfinder.R
import com.example.bookfinder.book.presentation.book_grid.components.previewBook
import com.example.bookfinder.book.presentation.book_screen.BookScreenAction
import com.example.bookfinder.book.presentation.book_screen.BookScreenState
import com.example.bookfinder.ui.theme.BookFinderTheme
import org.jsoup.Jsoup

@Composable
fun BookDetailScreen(
    state: BookScreenState,
    onDescriptionExpanded: (BookScreenAction) -> Unit,
    modifier: Modifier = Modifier
) {
    if(state.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        val book = state.selectedBook

        Scaffold(
            modifier = Modifier.padding(8.dp)
        ) { paddingValues ->
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = book.largeImageLink?.replace("http://", "https://"),
                    contentDescription = book.title,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier // stuff for size and spring
                        .fillMaxWidth()
                        .height(550.dp)
                        .align(Alignment.CenterHorizontally)
                        .padding(8.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))
                HorizontalDivider()
                Spacer(modifier = Modifier.height(8.dp))

                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text(
                        text = book.title,
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.displaySmall
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = book.authors.joinToString(),
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.headlineSmall
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))
                HorizontalDivider()
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = modifier.padding(top = 8.dp)
                ) {
                    Text(
                        text = Jsoup.parse(book.description).text()
                            ?: stringResource(R.string.no_description_was_found),
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = if (state.isDescriptionExpanded) Int.MAX_VALUE else 6,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    )
                }
                Button(
                    onClick = { onDescriptionExpanded(BookScreenAction.OnDescriptionChanged) },
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        .align(alignment = Alignment.CenterHorizontally)
                ) {
                    if (!state.isDescriptionExpanded) {
                        Text(
                            text = "Expand",
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    } else {
                        Text(
                            text = "Close",
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
                HorizontalDivider()
                Spacer(modifier = Modifier.height(8.dp))

                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text(
                        text = stringResource(R.string.date_published) + " " + (book.publishedDate?.formatted
                            ?: ""),
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = stringResource(R.string.page_count) + " " + (book.pageCount ?: ""),
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = stringResource(R.string.categories) + " " +
                                if(book.categories != null ) {
                                    book.categories.joinToString() }
                                else {
                                    stringResource(
                                        R.string.no_categories_found)
                                     },
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun BookDetailScreenPreview() {
    BookFinderTheme { 
        BookDetailScreen(
            state = BookScreenState(
                selectedBook = previewBook
            ),
            modifier = Modifier.background(MaterialTheme.colorScheme.background),
            onDescriptionExpanded = {}
        )
    }
}