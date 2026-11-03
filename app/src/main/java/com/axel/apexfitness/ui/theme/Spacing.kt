package com.axel.apexfitness.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class AppSpacing(
    val none: Dp = 0.dp,
    val extraSmall: Dp = 4.dp,
    val small: Dp = 8.dp,
    val medium: Dp = 12.dp,
    val regular: Dp = 16.dp,
    val large: Dp = 24.dp,
    val extraLarge: Dp = 48.dp
)

data class AppComponentSizes(
    // Logos
    val logoSizeLarge: Dp = 90.dp,

    // Iconos
    val iconSmall: Dp = 16.dp,
    val iconRegular: Dp = 20.dp,
    val iconMedium: Dp = 24.dp,

    // Contenedores de iconos
    val iconContainerMedium: Dp = 48.dp,

    // Cards
    val cardCornerRadius: Dp = 20.dp,
    val cardElevation: Dp = 3.dp,
    val memberCardHeight: Dp = 150.dp,

    // Botones
    val buttonHeight: Dp = 52.dp,
    val buttonCornerRadius: Dp = 50.dp,

    // Barra de progreso
    val progressBarHeight: Dp = 8.dp
)

val LocalAppSpacing = staticCompositionLocalOf { AppSpacing() }
val LocalAppComponentSizes = staticCompositionLocalOf { AppComponentSizes() }