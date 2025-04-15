package com.example.bookfinder.core.navigation

import android.widget.Toast
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.bookfinder.book.presentation.book_detail.BookDetailScreen
import com.example.bookfinder.book.presentation.book_screen.BookScreen
import com.example.bookfinder.book.presentation.book_screen.BookScreenAction
import com.example.bookfinder.book.presentation.book_screen.BookScreenEvent
import com.example.bookfinder.book.presentation.book_screen.BookScreenViewModel
import com.example.bookfinder.core.presentation.util.ObserveAsEvents
import com.example.bookfinder.core.presentation.util.toString
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun AdaptiveBookFinderDetailPane (
    modifier: Modifier = Modifier,
    viewModel: BookScreenViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    ObserveAsEvents(events = viewModel.events) { event ->
        when(event) {
            is BookScreenEvent.Error -> {
                Toast.makeText(
                    context,
                    event.error.toString(context),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    val navigator = rememberListDetailPaneScaffoldNavigator<Any>()
    val scope = rememberCoroutineScope()
    NavigableListDetailPaneScaffold(
        navigator = navigator,
        listPane = {
            AnimatedPane {
                BookScreen(
                    state = state,
                    onSearchTextChange = { action ->
                        viewModel.onAction(action)
                    },
                    onBookClick = {  action ->
                        viewModel.onAction(action)
                        when (action) {
                            is BookScreenAction.OnBookClick -> {
                                scope.launch {
                                    navigator.navigateTo(
                                        pane = ListDetailPaneScaffoldRole.Detail
                                    )
                                }
                            }
                            else -> {}
                        }
                    }
                )
            }
        },
        detailPane = {
            AnimatedPane {
                BookDetailScreen(
                    state = state,
                    onDescriptionExpanded = { action ->
                        viewModel.onAction(action)
                    }
                )
            }
        }
    )
}