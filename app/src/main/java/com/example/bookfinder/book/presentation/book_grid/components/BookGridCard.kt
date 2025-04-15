package com.example.bookfinder.book.presentation.book_grid.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.bookfinder.book.presentation.model.BookUi
import com.example.bookfinder.book.presentation.model.toDisplayableDate
import com.example.bookfinder.ui.theme.BookFinderTheme

@Composable
fun BookGridCard(
    bookUi: BookUi,
    onClick: (BookUi) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clickable(onClick = { onClick(bookUi) }),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (bookUi.imageLink != null) {
            AsyncImage(
                model = bookUi.imageLink.replace("http://", "https://"),
                contentDescription = bookUi.title,
                modifier = Modifier
                    .aspectRatio(0.75f)
                    .fillMaxSize()
            )
        } else {
            Text(
                text = "No Image Found",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyLarge
            )
        }
        Text(
            text = bookUi.title,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@PreviewLightDark
@Composable
private fun BookGridCardPreview() {
    BookFinderTheme {
        BookGridCard(
            bookUi = BookUi(
                imageLink = "https://books.google.com/books/content?id=ubERDAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api",
                title = "The History of Jazz",
                id = "ubERDAAAQBAJ",
                description = ""
            ),
            onClick = {},
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        )
    }
}

internal val previewBook = BookUi(
    imageLink = "https://books.google.com/books/content?id=ubERDAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api",
    title = "The History of Jazz",
    authors = listOf("Ted Gioia"),
    publisher = "Oxford University Press, USA",
    publishedDate = "2011-05-09".toDisplayableDate(),
    description = "Ted Gioia's History of Jazz has been universally hailed as a classic--acclaimed by jazz critics and fans around the world. Now Gioia brings his magnificent work completely up-to-date, drawing on the latest research and revisiting virtually every aspect of the music, past and present.Gioia tells the story of jazz as it had never been told before, in a book that brilliantly portrays the legendary jazz players, the breakthrough styles, and the world in which it evolved. Here are the giants of jazz and the great moments of jazz history--Jelly Roll Morton, Louis Armstrong, Duke Ellington at the Cotton Club, cool jazz greats such as Gerry Mulligan, Stan Getz, and Lester Young, Charlie Parker and Dizzy Gillespie's advocacy of modern jazz in the 1940s, Miles Davis's 1955 performance at the Newport Jazz Festival, Ornette Coleman's experiments with atonality, Pat Metheny's visionary extension of jazz-rock fusion, the contemporary sounds of Wynton Marsalis, and the post-modernists of the current day. Gioia provides the reader with lively portraits of these and many other great musicians, intertwined with vibrant commentary on the music they created. He also evokes the many worlds of jazz, taking the reader to the swamp lands of the Mississippi Delta, the bawdy houses of New Orleans, the rent parties of Harlem, the speakeasies of Chicago during the Jazz Age, the after hours spots of corrupt Kansas city, the Cotton Club, the Savoy, and the other locales where the history of jazz was made. And as he traces the spread of this protean form, Gioia provides much insight into the social context in which the music was born.",
    pageCount = 444,
    categories = listOf(
        "Music / History & Criticism",
        "Music / Genres & Styles / Jazz"
    ),
    largeImageLink = "http://books.google.com/books/publisher/content?id=ubERDAAAQBAJ&printsec=frontcover&img=1&zoom=4&edge=curl&imgtk=AFLRE71B7zdcTU3zmr5yJ9fqeCJMclB7cZNv8TEHNvJ9ldtWFZ3h-aB1NMlT8dQjU1YoHGIwzT3ET84k6uvK4hBZvPVHn2yhFspIVCBYFWb03_NuYznvpwv8ZU0ICoEZqS2leJ2_VMcn&source=gbs_api",
    id = "ubERDAAAQBAJ"
)