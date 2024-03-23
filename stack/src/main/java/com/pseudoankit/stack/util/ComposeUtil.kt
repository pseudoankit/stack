package com.pseudoankit.stack.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp

internal val Dp.toPx: Float
    @Composable get() {
        return toPx(LocalDensity.current)
    }

internal fun Dp.toPx(density: Density): Float {
    return with(density) {
        return@with toPx()
    }
}