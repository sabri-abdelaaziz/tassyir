package com.wagdev.noteapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF1E88E5), // Light Blue
    secondary = Color(0xFF00ACC1), // Cyan
    tertiary = Color(0xFF43A047),  // Green

    background = Color(0xFF121212), // Dark Grey
    surface = Color(0xFF121212), // Dark Grey
    onPrimary = Color(0xFFFFFFFF), // White
    onSecondary = Color(0xFFFFFFFF), // White
    onTertiary = Color(0xFFFFFFFF), // White
    onBackground = Color(0xFFE1E1E1), // Light Grey
    onSurface = Color(0xFFE1E1E1) // Light Grey
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF1E88E5), // Blue
    secondary = Color(0xFF00ACC1), // Cyan
    tertiary = Color(0xFF43A047), // Green

    background = Color(0xFFFFFFFF), // White
    surface = Color(0xFFFFFFFF), // White
    onPrimary = Color(0xFFFFFFFF), // White
    onSecondary = Color(0xFF000000), // Black
    onTertiary = Color(0xFFFFFFFF), // White
    onBackground = Color(0xFF000000), // Black
    onSurface = Color(0xFF000000) // Black
)


@Composable
fun NoteappTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}