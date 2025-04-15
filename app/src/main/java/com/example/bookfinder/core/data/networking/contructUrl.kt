package com.example.bookfinder.core.data.networking

import com.example.bookfinder.BuildConfig

fun constructUrl(url: String): String {
    val baseUrl = BuildConfig.BASE_URL.trimEnd('/') + '/' // Ensure base URL has exactly one trailing slash

    return when {
        url.startsWith("https://") || url.startsWith(baseUrl) -> url
        url.startsWith("/") -> baseUrl + url.drop(1) // Remove leading slash to avoid double slashes
        url.startsWith("http://") -> url.replace("http://", "https://")
        else -> baseUrl + url
    }
}