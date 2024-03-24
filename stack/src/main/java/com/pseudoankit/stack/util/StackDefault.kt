package com.pseudoankit.stack.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

internal object StackDefault {
    const val minChildCount = 1L
    const val maxChildCount = 4L

    val backstackViewHeight = 80.dp
    val upcomingViewHeight = 60.dp

    val bsContentTopPadding = 24.dp
    val bsContentHorizontalPadding = 24.dp
    val bsScrimColor get() = Color.Black.copy(alpha = .4f)

    val headerPadding = 16.dp
}