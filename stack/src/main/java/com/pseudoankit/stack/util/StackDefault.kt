package com.pseudoankit.stack.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

internal object StackDefault {
    const val MIN_CHILD_COUNT = 1L
    const val MAX_CHILD_COUNT = 4L
    val BACKSTACK_VIEW_HEIGHT = 80.dp
    val UPCOMING_VIEW_HEIGHT = 60.dp
    val BS_CONTENT_TOP_PADDING = 16.dp
    val BS_TOP_OFFSET = 35.dp
    val SCRIM_COLOR get() = Color.Black.copy(alpha = .4f)
}