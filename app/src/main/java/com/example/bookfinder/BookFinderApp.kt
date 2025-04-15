package com.example.bookfinder

import android.app.Application
import com.example.bookfinder.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BookFinderApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BookFinderApp)
            androidLogger()

            modules(appModule)
        }
    }
}