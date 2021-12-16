package com.sample.android.tmdb.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

private val LocalCustomFontSizes = staticCompositionLocalOf { FontSizes() }

object TmdbTheme {
    val fontSizes: FontSizes
        @Composable
        get() = LocalCustomFontSizes.current
}

@Immutable
data class FontSizes(
    val sp_11: TextUnit = 11.sp
)

val TmdbBlackLight = Color(0xFF333333)
val TmdbBlackDark = Color(0xFF1E1E1E)
val TmdbGreen = Color(0xFF4CAF50)

private val colors = darkColors(
    primary = TmdbBlackLight,
    primaryVariant = TmdbBlackDark,
    secondary = TmdbGreen
)

@Composable
fun TmdbTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = colors,
        content = content
    )
}