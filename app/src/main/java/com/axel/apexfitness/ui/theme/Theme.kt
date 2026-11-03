package com.axel.apexfitness.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

private val ApexDarkColorScheme = darkColorScheme(
    primary = AppPrimary,
    onPrimary = AppBackground,

    secondary = AppSecondary,
    onSecondary = AppBackground,

    tertiary = AppPrimary,
    onTertiary = AppBackground,

    background = AppBackground,
    onBackground = AppForeground,

    surface = AppSurface,
    onSurface = AppForeground,

    surfaceVariant = AppSurface,
    onSurfaceVariant = AppTextSecondary,

    outline = AppTextSecondary
)

@Composable
fun ApexFitnessTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    // La app usa siempre el esquema oscuro, coherente con la plantilla de Behance
    CompositionLocalProvider(
        LocalAppSpacing provides AppSpacing(),
        LocalAppComponentSizes provides AppComponentSizes()
    ) {
        MaterialTheme(
            colorScheme = ApexDarkColorScheme,
            typography = Typography,
            content = content
        )
    }
}

object AppTheme {
    val spacing: AppSpacing
        @Composable
        @ReadOnlyComposable
        get() = LocalAppSpacing.current

    val sizes: AppComponentSizes
        @Composable
        @ReadOnlyComposable
        get() = LocalAppComponentSizes.current
}
