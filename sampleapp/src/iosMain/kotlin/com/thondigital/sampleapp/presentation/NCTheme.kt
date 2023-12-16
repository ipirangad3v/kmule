package com.thondigital.sampleapp.presentation

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.thondigital.sampleapp.presentation.ui.theme.LightColorScheme

@Composable
actual fun NCTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = LightColorScheme,
        content = content
    )
}
