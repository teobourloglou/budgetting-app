package com.example.budgettingapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColors = darkColorScheme(
    primary = nthng_theme_primary,
    onPrimary = nthng_theme_on_primary,
    secondary = nthng_theme_secondary,
    onSecondary = nthng_theme_on_secondary,
    tertiary = nthng_theme_tertiary,
    onTertiary = nthng_theme_on_tertiary,
    background = nthng_theme_background,
    onBackground = nthng_theme_on_background,
    surface = nthng_theme_background,
    onSurface = nthng_theme_on_background,
    error = nthng_theme_primary,
    onError = nthng_theme_on_primary,
    outline = nthng_theme_outline
)

private val LightColors = lightColorScheme(
    primary = nthng_theme_primary,
    onPrimary = nthng_theme_on_primary,
    secondary = nthng_theme_secondary,
    onSecondary = nthng_theme_on_secondary,
    tertiary = nthng_theme_tertiary,
    onTertiary = nthng_theme_on_tertiary,
    background = nthng_theme_background,
    onBackground = nthng_theme_on_background,
    surface = nthng_theme_background,
    onSurface = nthng_theme_on_background,
    error = nthng_theme_primary,
    onError = nthng_theme_on_primary,
    outline = nthng_theme_outline

)

@Composable
fun BudgettingAppTheme(
        darkTheme: Boolean = isSystemInDarkTheme(),
        // Dynamic color is available on Android 12+
        dynamicColor: Boolean = true,
        content: @Composable () -> Unit
) {
    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }

        darkTheme -> DarkColors
        else -> LightColors
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
    )
}