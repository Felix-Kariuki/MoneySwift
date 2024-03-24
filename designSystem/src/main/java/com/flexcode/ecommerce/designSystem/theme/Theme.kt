package com.flexcode.ecommerce.designSystem.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext

// private val DarkColorScheme = darkColorScheme(
//    primary = DarkBlue,
//    secondary = IconDisabled,
//    tertiary = Pink80,
//    onPrimary = Blue,
//    outline = Grey46,
//    background = DarkBlue,
//    surface = Grey14,
//    inversePrimary = Color.White,
//    onSurface = Grey14
// )
//
// private val LightColorScheme = lightColorScheme(
//    primary = Blue,
//    secondary = IconDisabled,
//    tertiary = Pink40,
//    onPrimary = Blue,
//    outline = Grey96,
//    background = Color.White,
//    surface = Grey96,
//    inversePrimary = Grey13,
//    onSurface = Color.White
// )

private val DarkColorScheme =
    darkColorScheme(
        primary = primaryColor,
        secondary = primaryColor,
        tertiary = secondaryColor,
        background = primaryColor,
    )

private val LightColorScheme =
    lightColorScheme(
        primary = primaryColor,
        secondary = primaryColor,
        tertiary = secondaryColor,
        background = primaryColor,
    )

@Composable
fun EcommerceTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
//    val view = LocalView.current
//    if (!view.isInEditMode) {
//        SideEffect {
//            val window = (view.context as Activity).window
//            window.statusBarColor = colorScheme.primary.toArgb()
//            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
//        }
//    }
    CompositionLocalProvider(
        LocalSpacing provides Spacing(),
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = typography,
            shapes = shapes,
            content = content,
        )
    }
}
