package com.rajan.CoffeeShop.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = CoffeePrimaryLight,
    onPrimary = CoffeeOnPrimaryLight,
    background = CoffeeBackgroundLight,
    onBackground = CoffeeOnBackgroundLight,
    surface = CoffeeSurfaceLight,
    secondary = CoffeeSecondaryLight,
    error = CoffeeErrorLight
)

private val DarkColorScheme = darkColorScheme(
    primary = CoffeePrimaryDark,
    onPrimary = CoffeeOnPrimaryDark,
    background = CoffeeBackgroundDark,
    onBackground = CoffeeOnBackgroundDark,
    surface = CoffeeSurfaceDark,
    secondary = CoffeeSecondaryDark,
    error = CoffeeErrorDark
)

@Composable
fun CoffeeShopTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}