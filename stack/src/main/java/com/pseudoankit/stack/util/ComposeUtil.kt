package com.pseudoankit.stack.util

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
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

internal fun Modifier.clickable(
    enabled: Boolean = true,
    showRipple: Boolean = true,
    onClick: () -> Unit
): Modifier = this.composed {
    this.clickable(
        enabled = enabled,
        indication = if (showRipple) LocalIndication.current else null,
        interactionSource = remember { MutableInteractionSource() },
        onClick = onClick
    )
}
