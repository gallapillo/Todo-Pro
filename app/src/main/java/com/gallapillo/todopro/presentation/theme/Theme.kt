package com.gallapillo.todopro.presentation.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200,
    background = Background
)

@Composable
fun TodoProTheme(content: @Composable() () -> Unit) {

    rememberSystemUiController().setSystemBarsColor(
        color = Primary,
        darkIcons = false
    )

    MaterialTheme(
        colors = DarkColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}