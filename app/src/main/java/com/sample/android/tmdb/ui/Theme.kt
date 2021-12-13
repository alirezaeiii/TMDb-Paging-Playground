package com.sample.android.tmdb.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun TmdbTheme(content: @Composable() () -> Unit) {
    MaterialTheme(
        colors = darkColors(
            primary = Color(0xFF333333),
            primaryVariant = Color(0xFF1E1E1E),
            secondary = Color(0xFF4CAF50)
        ),
        content = content
    )
}