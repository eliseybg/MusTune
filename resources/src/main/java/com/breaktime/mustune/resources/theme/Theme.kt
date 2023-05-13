package com.breaktime.mustune.resources.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.breaktime.mustune.resources.theme.colors.DefaultColors
import com.breaktime.mustune.resources.theme.colors.LocalColors
import com.breaktime.mustune.resources.theme.colors.MusTuneColors
import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import com.breaktime.mustune.resources.theme.colors.darkColors
import com.breaktime.mustune.resources.theme.colors.lightColors

@Composable
fun MusTuneTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    CompositionLocalProvider(
        LocalColors provides if (darkTheme) darkColors else lightColors,
    ) {
        MaterialTheme(
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}

object MusTuneTheme {
    val colors: MusTuneColors
        @Composable
        get() = LocalColors.current

    val typography: Typography
        @Composable
        get() = MaterialTheme.typography

    val shapes: Shapes
        @Composable
        get() = MaterialTheme.shapes
}