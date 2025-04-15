package com.example.bookfinder.di

import com.example.bookfinder.book.data.networking.RemoteBookDataSource
import com.example.bookfinder.book.domain.BookDataSource
import com.example.bookfinder.book.presentation.book_screen.BookScreenViewModel
import com.example.bookfinder.core.data.networking.HttpClientFactory
import io.ktor.client.engine.cio.CIO
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single { HttpClientFactory.create(CIO.create()) }
    singleOf(::RemoteBookDataSource).bind<BookDataSource>()

    viewModelOf(::BookScreenViewModel)
}