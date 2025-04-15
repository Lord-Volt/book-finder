package com.example.bookfinder.book.presentation.book_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookfinder.book.domain.BookDataSource
import com.example.bookfinder.book.presentation.model.toBookUi
import com.example.bookfinder.core.domain.util.onError
import com.example.bookfinder.core.domain.util.onSuccess
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookScreenViewModel(
    private val bookDataSource: BookDataSource
): ViewModel() {

    private val _state = MutableStateFlow(BookScreenState())
    val state = _state.asStateFlow()

    private val _searchQuery = MutableStateFlow("")

    @OptIn(FlowPreview::class)
    val searchQuery = _searchQuery
        .debounce(250)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ""
        )

    init {
        searchQuery
            .drop(1) // Prevents initial unnecessary call
            .onEach { query ->
                if (query.isNotBlank()) {
                    getBooks(query) // Fetch only if query is not empty
                } else {
                    _state.update {
                        it.copy(books = emptyList())
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    fun onAction(action: BookScreenAction) {
        when (action) {
            is BookScreenAction.OnSearchTextChange -> {
                if(action.searchText.isNotBlank()) {
                    _state.update {
                        it.copy(
                            searchFieldText = action.searchText,
                            isLoading = true
                        )
                    }
                } else {
                    _state.update {
                        it.copy(
                            searchFieldText = action.searchText,
                            isLoading = false
                        )
                    }
                }
                _searchQuery.value = action.searchText
            }
            is BookScreenAction.OnBookClick -> {
                _state.update {
                    it.copy(
                        selectedBook = action.bookUi,
                        isLoading = true
                    )
                }
                getBookDetail(state.value.selectedBook.id)
            }
            is BookScreenAction.OnDescriptionChanged -> {
                _state.update {
                    it.copy(isDescriptionExpanded = !it.isDescriptionExpanded)
                }
            }
        }
    }

    private val _events = Channel<BookScreenEvent>()
    val events = _events.receiveAsFlow()

    private fun getBooks(query: String) {
        viewModelScope.launch {
            bookDataSource
                .getBooks(query.replace(" ", "+"))
                .onSuccess { books ->
                    _state.update { it.copy(
                        isLoading = false,
                        books = books.map { it.toBookUi() }
                    ) }
                }
                .onError { error ->
                    _state.update { it.copy(
                        isLoading = false,
                        books = emptyList()
                    ) }
                    _events.send(BookScreenEvent.Error(error))
                }
        }
    }

    private fun getBookDetail(query: String) {
        viewModelScope.launch {
            bookDataSource
                .getBookDetail(query)
                .onSuccess { bookDetail ->
                    _state.update { it.copy(
                        isLoading = false,
                        selectedBook = bookDetail.toBookUi()
                    ) }
                }
                .onError { error ->
                    _state.update {
                        it.copy(
                            isLoading = false
                        ) }
                    _events.send(BookScreenEvent.Error(error))
                }
        }
    }
}